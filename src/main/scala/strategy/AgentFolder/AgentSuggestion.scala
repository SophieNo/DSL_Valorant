package strategy.AgentFolder
import play.api.libs.json._
import scala.io.Source
import strategy.map.*
import strategy.AgentFolder.*

object AgentSuggestion {

  // Parser les données
  implicit val agentReads: Reads[Agent] = Json.reads[Agent]
  implicit val mapReads: Reads[Map] = Json.reads[Map]

  // Charge agents depuis le fichier JSON
  def loadAgents(): List[Agent] = {
    val jsonString = Source.fromFile("src/main/scala/resources/agents.json").getLines().mkString
    Json.parse(jsonString).as[List[Agent]]
  }

  // Charge maps depuis le fichier JSON
  def loadMaps(): List[Map] = {
    val jsonString = Source.fromFile("src/main/scala/resources/maps.json").getLines().mkString
    Json.parse(jsonString).as[List[Map]]
  }

  // Fonction de suggestion des agents
  def suggestAgents(mapName: String, selectedAgents: List[String]): List[Agent] = {
    val agents = loadAgents()

    // Calcul du nombre d'agents manquants
    val numberOfAgentsMissing = Math.max(5 - selectedAgents.size, 0)

    // On filtre les agents non encore sélectionnés
    val availableAgents = agents.filterNot(agent => selectedAgents.map(_.toLowerCase).contains(agent.name.toLowerCase))

    // On regroupe les agents par rôle
    val groupedByRole = availableAgents.groupBy(_.role)

    // Priorité : un agent par rôle
    val prioritizedAgents = List(
      groupedByRole.get("Contrôleur").flatMap(_.headOption),
      groupedByRole.get("Duelliste").flatMap(_.headOption),
      groupedByRole.get("Initiateur").flatMap(_.headOption),
      groupedByRole.get("Sentinelle").flatMap(_.headOption)
    ).flatten

    // Compléter avec des agents restants
    val remainingAgents = availableAgents.diff(prioritizedAgents)

    // Ne suggérer que le nombre d'agents manquants
    (prioritizedAgents ++ remainingAgents).take(numberOfAgentsMissing)
  }
}
