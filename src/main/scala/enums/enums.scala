package enums

object Map extends Enumeration {
  type Map = Value
  val Ascent, Bind, Haven, Split, Icebox, Breeze, Fracture, Pearl, Lotus = Value
}


object RoundType extends Enumeration {
  type RoundType = Value
  val FullBuy, ForceBuy, Eco, PistolRound = Value
}


object Playstyle extends Enumeration {
  type Playstyle = Value
  val Aggressive, Defensive, Balanced = Value
}


object Character extends Enumeration {
  type Character = Value
  val Jett, Omen, Viper, Sage, Sova, Reyna, Phoenix = Value
}

