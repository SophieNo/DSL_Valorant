package strategy

import strategy.Strategy
import strategy.Agent.Agent

import strategy.map.*

object StrategyGenerator {

  
  /**
   * Génère une stratégie en fonction de la carte et de l'agent.
   *
   * @param map La carte choisie.
   * @param agent L'agent choisi.
   * @return Une description de stratégie.
   */
  def generateStrategy(map: Map, agent: Agent): Strategy = {
    val strategyDescription = (map.name, agent.role) match {
      case ("Bind", "Controller") =>
        s"Utilisez les fumigènes de ${agent.name} pour bloquer les lignes de vue sur les téléporteurs."

      case ("Haven", "Duelist") =>
        s"Utilisez les capacités offensives de ${agent.name} pour créer de la pression sur le site C."

      case ("Split", "Sentinel") =>
        s"Placez les pièges de ${agent.name} pour contrôler les entrées des sites A et B."

      case ("Icebox", "Initiator") =>
        s"Utilisez les capacités de reconnaissance de ${agent.name} pour localiser les ennemis sur les hauteurs."

      case _ =>
        s"Aucune stratégie spécifique disponible pour ${agent.name} sur ${map.name}."
    }

    Strategy(map.name, agent.name, strategyDescription)
  }
}
