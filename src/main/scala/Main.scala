package valorant

// Définition des agents et équipements
case class Agent(name: String)
case class Equipment(name: String, cost: Int)
case class Player(agent: Agent, credits: Int, equipment: List[Equipment])

// DSL pour recommander des achats
object BuyOptimizer {

  val phantom = Equipment("Phantom", 2900)
  val vandal = Equipment("Vandal", 2900)
  val spectre = Equipment("Spectre", 1600)
  val shield = Equipment("Light Shield", 400)
  val heavyShield = Equipment("Heavy Shield", 1000)
  val abilities = Equipment("Ability", 200)

  // Fonction pour recommander des achats individuels
  def recommend(player: Player): List[Equipment] = {
    if (player.credits >= 3900) List(vandal, heavyShield)
    else if (player.credits >= 2500) List(phantom, shield)
    else if (player.credits >= 1600) List(spectre, shield)
    else List(abilities)
  }

  // Fonction pour recommander des achats pour une équipe
  def teamRecommendation(players: List[Player]): Map[String, List[Equipment]] = {
    players.map { player =>
      player.agent.name -> recommend(player)
    }.toMap
  }
}

// DSL pour construire des équipes et générer des recommandations
object ValorantDSL {

  // Créer un joueur
  def player(agentName: String, credits: Int): Player = {
    Player(Agent(agentName), credits, List())
  }

  // Créer une équipe
  def team(players: Player*): List[Player] = players.toList

  // Générer des recommandations pour une équipe
  def recommendForTeam(players: List[Player]): Map[String, List[Equipment]] = {
    BuyOptimizer.teamRecommendation(players)
  }

  // Générer une description lisible des recommandations
  def displayRecommendations(recommendations: Map[String, List[Equipment]]): String = {
    recommendations.map { case (agent, items) =>
      s"$agent devrait acheter : ${items.map(_.name).mkString(", ")}"
    }.mkString("\n")
  }
}
