package assistant

import valorantdsl.*
import enums.*
import strategy.map.NaturalLanguageInterpreter
import strategy.AgentFolder.AgentSuggestion
import strategy.weapon.Recommender

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
}
