package strategy

import strategy.StrategyGenerator
import strategy.Agent.AgentDataParser
import strategy.map.MapDataParser
import strategy.*
import scala.io.StdIn.readLine

object RecommendationAgentMap extends App {

  val maps = MapDataParser.getMapData
  val agents = AgentDataParser.getAgentData

  // Sélectionner une carte
  println("Choisissez une carte :")
  maps.zipWithIndex.foreach { case (map, index) =>
    println(s"${index + 1}. ${map.name}")
  }
  val mapChoice = readLine("Entrez le numéro de la carte : ").toInt
  val selectedMap = maps(mapChoice - 1)

  // Sélectionner un agent
  println("\nChoisissez un agent :")
  agents.zipWithIndex.foreach { case (agent, index) =>
    println(s"${index + 1}. ${agent.name} (${agent.role})")
  }
  val agentChoice = readLine("Entrez le numéro de l'agent : ").toInt
  val selectedAgent = agents(agentChoice - 1)

  // Générer et afficher la stratégie
  val strategy = StrategyGenerator.generateStrategy(selectedMap, selectedAgent)
  println("\nStratégie Générée :")
  println(s"Carte : ${strategy.map}")
  println(s"Agent : ${strategy.agent}")
  println(s"Description : ${strategy.description}")
}
