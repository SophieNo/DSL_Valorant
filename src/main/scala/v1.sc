import scala.io.StdIn.readLine

@main def hello(): Unit =
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"

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

  def recommend(player: Player): List[Equipment] = {
    if (player.credits >= 3900) List(vandal, heavyShield)
    else if (player.credits >= 2500) List(phantom, shield)
    else if (player.credits >= 1600) List(spectre, shield)
    else List(abilities)
  }

  def teamRecommendation(players: List[Player]): Map[String, List[Equipment]] = {
    players.map { player =>
      player.agent.name -> recommend(player)
    }.toMap
  }
}

// Partie interactive
object StrategyExample extends App {

  // Fonction pour créer un joueur interactif
  def createPlayer(): Player = {
    val agent = readLine("Entrez l'agent (ex: Jett, Omen, Sova) : ")
    val credits = readLine(s"Entrez les crédits pour $agent : ").toInt

    Player(Agent(agent), credits, List())
  }

  println("Création de l'équipe Valorant...")

  val numberOfPlayers = readLine("Combien de joueurs dans l'équipe ? ").toInt
  val team = (1 to numberOfPlayers).map { _ =>
    createPlayer()
  }.toList

  val recommendations = BuyOptimizer.teamRecommendation(team)

  println("\n--- Recommandations d'achats ---")
  recommendations.foreach { case (agent, items) =>
    println(s"$agent devrait acheter : ${items.map(_.name).mkString(", ")}")
  }
}