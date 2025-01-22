package assistant

import valorantdsl.*
import enums.*
import strategy.map.NaturalLanguageInterpreter
import strategy.AgentFolder.AgentSuggestion
import strategy.StrategyGenerator
import strategy.weapon.Recommender
import strategy.map.Map
import strategy.AgentFolder.Agent
import strategy.map.MapDataParser
import strategy.AgentFolder.AgentDataParser
import strategy.RecommendationAgentMap

object ValorantAssistant {
  def ask(question: String): Unit = {
    val interpretation = NaturalLanguageInterpreter.interpret(question)

    interpretation.split(":") match {
      case Array("composition", map, role, agents) =>
        val selectedAgents = if (agents != null) agents.split(",").map(_.trim).toList else List.empty[String]
        val suggestions = AgentSuggestion.suggestAgents(map, selectedAgents).map(_.name)
        println(s"Suggestions pour $map${if (role != null) s" en $role" else ""} : " + suggestions.mkString(", "))

      case Array("buyingadvice", credits, agent, map, roundType, playstyle, equipment) =>
        handleBuyingAdvice(credits.toInt, Option(agent), Option(map), Option(roundType), Option(playstyle), Option(equipment))

      case Array("buyingadvice", credits, agent, map, roundType, playstyle) =>
        handleBuyingAdvice(credits.toInt, Option(agent), Option(map), Option(roundType), Option(playstyle))

      case Array("buyingadvice", credits, agent, map, roundType) =>
        handleBuyingAdvice(credits.toInt, Option(agent), Option(map), Option(roundType))

      case Array("buyingadvice", credits, agent, map) =>
        handleBuyingAdvice(credits.toInt, Option(agent), Option(map))

      case Array("buyingadvice", credits, agent) =>
        handleBuyingAdvice(credits.toInt, Option(agent))

      case Array("buyingadvice", credits) =>
        handleBuyingAdvice(credits.toInt)

      case Array("strategy", map, agent) =>
        generateStrategy(map, agent)

      case _ =>
        println("Format de question invalide. Consultez la documentation pour les formats acceptés.")
    }
  }

  private def handleBuyingAdvice(
    credits: Int,
    agentOpt: Option[String] = None,
    mapOpt: Option[String] = None,
    roundTypeOpt: Option[String] = None,
    playstyleOpt: Option[String] = None,
    equipmentOpt: Option[String] = None
  ): Unit = {
    val roundEnum = roundTypeOpt.flatMap(name => RoundType.values.find(value => value.toString.equalsIgnoreCase(name)))
    val playstyleEnum = playstyleOpt.flatMap(name => Playstyle.values.find(value => value.toString.equalsIgnoreCase(name)))
    val ownedItems = equipmentOpt.map(_.split(",").map(Subtype.withName).toSet).getOrElse(Set.empty[Subtype.Subtype])

    agentOpt match {
      case Some(agent) =>
        Character.values.find(value => value.toString.equalsIgnoreCase(agent)) match {
          case Some(validAgent) =>
            validAgent
              .playWith(credits.credits)
              .inRound(roundEnum.getOrElse(RoundType.FullBuy))
              .preferredAs(playstyleEnum.getOrElse(Playstyle.Aggressive))
              .owned(ownedItems)
              .build(5.items)
          case None =>
            println(s"Agent $agent non valide.")
        }
      case None =>
        println(s"Crédits disponibles : $credits. Conseil : Optez pour un équipement équilibré avec vos crédits.")
    }
  }

  private def generateStrategy(mapName: String, agentName: String): Unit = {
    val maps = MapDataParser.getMapData
    val agents = AgentDataParser.getAgentData

    val selectedMap = maps.find(_.name.equalsIgnoreCase(mapName))
    val selectedAgent = agents.find(_.name.equalsIgnoreCase(agentName))

    (selectedMap, selectedAgent) match {
      case (Some(map), Some(agent)) =>
        val strategy = StrategyGenerator.generateStrategy(map, agent)
        println(s"Stratégie : ${strategy.description}")
      case (None, _) =>
        println(s"Erreur : La carte '$mapName' est introuvable.")
      case (_, None) =>
        println(s"Erreur : L'agent '$agentName' est introuvable.")
    }
  }
}
