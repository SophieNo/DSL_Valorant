package strategy

import strategy.Strategy
import strategy.AgentFolder.Agent

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
    // Bind
    case ("Bind", "Contrôleur") =>
      s"Utilisez les fumigènes de ${agent.name} pour bloquer les lignes de vue sur les téléporteurs."
    case ("Bind", "Duelliste") =>
      s"Utilisez les capacités explosives et de mobilité de ${agent.name} pour prendre rapidement le site A ou B."
    case ("Bind", "Sentinelle") =>
      s"Placez les pièges ou capacités défensives de ${agent.name} pour sécuriser les rotations et empêcher les infiltrations."
    case ("Bind", "Initiateur") =>
      s"Utilisez les capacités de reconnaissance de ${agent.name} pour obtenir des informations dans les zones encombrées."

    // Haven
    case ("Haven", "Contrôleur") =>
      s"Utilisez les fumées ou capacités de contrôle de ${agent.name} pour sécuriser le site B ou diviser les lignes de tir sur les trois sites."
    case ("Haven", "Duelliste") =>
      s"Utilisez les capacités offensives de ${agent.name} pour créer de la pression sur le site C."
    case ("Haven", "Sentinelle") =>
      s"Déployez les pièges et capacités de ${agent.name} pour verrouiller les accès aux sites A et C."
    case ("Haven", "Initiateur") =>
      s"Utilisez les capacités de reconnaissance de ${agent.name} pour révéler les ennemis dans la midlane et faciliter les pushs."

    // Split
    case ("Split", "Contrôleur") =>
      s"Utilisez les capacités de ${agent.name} pour couper les lignes de tir au mid et faciliter la prise des sites A et B."
    case ("Split", "Duelliste") =>
      s"Utilisez les capacités de mobilité de ${agent.name} pour rapidement engager les adversaires sur les hauteurs."
    case ("Split", "Sentinelle") =>
      s"Placez les pièges de ${agent.name} pour contrôler les entrées des sites A et B."
    case ("Split", "Initiateur") =>
      s"Utilisez les capacités de ${agent.name} pour préparer des pushs coordonnés en midlane."

    // Icebox
    case ("Icebox", "Contrôleur") =>
      s"Utilisez les fumées ou capacités de contrôle de ${agent.name} pour bloquer la vue sur les hauteurs des sites."
    case ("Icebox", "Duelliste") =>
      s"Profitez des capacités de ${agent.name} pour exploiter la verticalité des zones et surprendre vos adversaires."
    case ("Icebox", "Sentinelle") =>
      s"Déployez les pièges de ${agent.name} pour surveiller les rotations et sécuriser les plantings."
    case ("Icebox", "Initiateur") =>
      s"Utilisez les capacités de reconnaissance de ${agent.name} pour localiser les ennemis sur les hauteurs."

    // Ascent
    case ("Ascent", "Contrôleur") =>
      s"Utilisez les capacités de ${agent.name} pour sécuriser le midlane et diviser les lignes de vue sur les sites A et B."
    case ("Ascent", "Duelliste") =>
      s"Utilisez les capacités de ${agent.name} pour rapidement entrer sur les sites A ou B."
    case ("Ascent", "Sentinelle") =>
      s"Placez les pièges et capacités défensives de ${agent.name} pour contrôler les rotations sur le midlane."
    case ("Ascent", "Initiateur") =>
      s"Utilisez les capacités de reconnaissance de ${agent.name} pour aider à prendre le contrôle du midlane."

    // Breeze
    case ("Breeze", "Contrôleur") =>
      s"Utilisez les capacités de ${agent.name} pour bloquer les longues lignes de tir sur les sites A et B."
    case ("Breeze", "Duelliste") =>
      s"Profitez de la mobilité de ${agent.name} pour surprendre les ennemis dans les vastes espaces ouverts."
    case ("Breeze", "Sentinelle") =>
      s"Placez vos capacités pour contrôler les entrées des vastes sites et empêcher les rotations rapides."
    case ("Breeze", "Initiateur") =>
      s"Utilisez les capacités de ${agent.name} pour obtenir des informations sur les longues distances."

    // Fracture
    case ("Fracture", "Contrôleur") =>
      s"Utilisez les fumées ou capacités de ${agent.name} pour diviser les lignes de tir et sécuriser les passages."
    case ("Fracture", "Duelliste") =>
      s"Utilisez les capacités offensives de ${agent.name} pour engager rapidement sur les points de pincement."
    case ("Fracture", "Sentinelle") =>
      s"Placez les pièges ou capacités de ${agent.name} pour verrouiller les passages vers les sites."
    case ("Fracture", "Initiateur") =>
      s"Utilisez les capacités de ${agent.name} pour révéler les ennemis et préparer les pushs sur les sites."

    // Pearl
    case ("Pearl", "Contrôleur") =>
      s"Utilisez les fumées de ${agent.name} pour couper les lignes de vue sur les sites confinés."
    case ("Pearl", "Duelliste") =>
      s"Utilisez les capacités explosives de ${agent.name} pour forcer les ennemis hors des angles serrés."
    case ("Pearl", "Sentinelle") =>
      s"Placez vos capacités pour surveiller les couloirs étroits et les angles rapprochés."
    case ("Pearl", "Initiateur") =>
      s"Utilisez les capacités de reconnaissance de ${agent.name} pour nettoyer les zones confinées avant d'engager."

    // Lotus
    case ("Lotus", "Contrôleur") =>
      s"Utilisez les fumées ou capacités de ${agent.name} pour contrôler les rotations entre les trois sites."
    case ("Lotus", "Duelliste") =>
      s"Utilisez les capacités de ${agent.name} pour exploiter les angles complexes et forcer les engagements."
    case ("Lotus", "Sentinelle") =>
      s"Placez les pièges ou capacités de ${agent.name} pour sécuriser les points de rotation."
    case ("Lotus", "Initiateur") =>
      s"Utilisez les capacités de ${agent.name} pour nettoyer les sites avant d'engager."

    // Default Case
    case _ =>
      s"Aucune stratégie spécifique disponible pour ${agent.name} sur ${map.name}."
    }

    Strategy(map.name, agent.name, strategyDescription)
  }
}
