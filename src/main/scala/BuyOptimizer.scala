import strategy.*
import enums.*

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
    character: Character.Character,
    credits: Int,
    map: Option[Map.Map] = None,
    roundType: Option[RoundType.RoundType] = None,
    preferredStyle: Option[Playstyle.Playstyle] = None
  )

  extension (amount: Int) {
    def credits: Int = amount
  }

  extension (character: Character.Character) {
    def playWith(credits: Int): SetupBuilder = new SetupBuilder(character, credits)
  }

  extension (n: Int) {
    def items: Int = n
  }

  class SetupBuilder(character: Character.Character, credits: Int) {
  private var map: Option[Map.Map] = None
  private var roundType: Option[RoundType.RoundType] = None
  private var preferredStyle: Option[Playstyle.Playstyle] = None

  def onMap(selectedMap: Map.Map): this.type = {
    map = Some(selectedMap)
    this
  }

  def inRound(selectedRound: RoundType.RoundType): this.type = {
    roundType = Some(selectedRound)
    this
  }

  def preferredAs(style: Playstyle.Playstyle): this.type = {
    preferredStyle = Some(style)
    this
  }

  def build(topN: Int): Unit = {
    val setup = PlayerSetup(character, credits, map, roundType, preferredStyle)
    Recommender.recommend(setup, Some(character.toString), topN)
  }
}
}
