import strategy.*

package object valorantdsl {
    //suggestions de copilot j verrai plus tard hein
//   // TODO: Add more roles
//   sealed trait Role
//   case object Tank extends Role
//   case object Support extends Role
//   case object Damage extends Role

//   // TODO: Add more maps
//   sealed trait Map
//   case object SummonersRift extends Map
//   case object HowlingAbyss extends Map
//   case object TwistedTreeline extends Map

//   // TODO: Add more rounds
//   sealed trait Round
//   case object Normal extends Round
//   case object Ranked extends Round
//   case objectARAM extends Round

//   // TODO: Add more preferred styles
//   sealed trait PreferredStyle
  case class PlayerSetup(
    role: Option[String] = None,
    credits: Int,
    map: Option[String] = None,
    roundType: Option[String] = None,
    preferredStyle: Option[String] = None
  )

  extension (amount: Int) {
    def credits: Int = amount
  }

  extension (role: String) {
    def playWith(credits: Int): SetupBuilder = new SetupBuilder(role, credits)
  }

  class SetupBuilder(role: String, credits: Int) {
  private var map: Option[String] = None
  private var roundType: Option[String] = None
  private var preferredStyle: Option[String] = None

  def onMap(mapName: String): this.type = {
    map = Some(mapName)
    this
  }

  def inRound(round: String): this.type = {
    roundType = Some(round)
    this
  }

  def preferredAs(style: String): this.type = {
    preferredStyle = Some(style)
    this
  }

  def build(): Unit = {
    val setup = PlayerSetup(Some(role), credits, map, roundType, preferredStyle)
    println(Recommender.recommendWeapon(setup))
  }
}
}
