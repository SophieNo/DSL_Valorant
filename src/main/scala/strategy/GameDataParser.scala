package strategy

import io.circe.generic.auto._
import io.circe.parser.decode
import scala.io.Source
import io.circe.*
import enums.*

object GameDataParser {
  
  implicit val subtypeDecoder: Decoder[Subtype.Value] = Decoder.decodeString.emap {
    case "Rifle"   => Right(Subtype.Rifle)
    case "Shield"  => Right(Subtype.Shield)
    case "Ability" => Right(Subtype.Ability)
    case "Utility" => Right(Subtype.Utility)
    case other     => Left(s"Invalid subtype: $other")
  }

  implicit val mapDecoder: Decoder[Map.Value] = Decoder.decodeString.emap {
    case "Ascent"  => Right(Map.Ascent)
    case "Bind"    => Right(Map.Bind)
    case "Haven"   => Right(Map.Haven)
    case "Split"   => Right(Map.Split)
    case "Icebox"  => Right(Map.Icebox)
    case "Breeze"  => Right(Map.Breeze)
    case "Fracture"=> Right(Map.Fracture)
    case "Pearl"   => Right(Map.Pearl)
    case "Lotus"   => Right(Map.Lotus)
    case other     => Left(s"Invalid map: $other")
  }

  implicit val roundTypeDecoder: Decoder[RoundType.Value] = Decoder.decodeString.emap {
    case "FullBuy"    => Right(RoundType.FullBuy)
    case "ForceBuy"   => Right(RoundType.ForceBuy)
    case "Eco"        => Right(RoundType.Eco)
    case "PistolRound"=> Right(RoundType.PistolRound)
    case other        => Left(s"Invalid round type: $other")
  }

  implicit val playstyleDecoder: Decoder[Playstyle.Value] = Decoder.decodeString.emap {
    case "Aggressive" => Right(Playstyle.Aggressive)
    case "Defensive"  => Right(Playstyle.Defensive)
    case "Balanced"   => Right(Playstyle.Balanced)
    case other        => Left(s"Invalid playstyle: $other")
  }

  implicit val characterDecoder: Decoder[Character.Value] = Decoder.decodeString.emap {
    case "Jett"    => Right(Character.Jett)
    case "Omen"    => Right(Character.Omen)
    case "Viper"   => Right(Character.Viper)
    case "Sage"    => Right(Character.Sage)
    case "Sova"    => Right(Character.Sova)
    case "Reyna"   => Right(Character.Reyna)
    case "Phoenix" => Right(Character.Phoenix)
    case other     => Left(s"Invalid character: $other")
  }

  private lazy val gameData: GameData = {
    val jsonStr =  scala.io.Source.fromFile("src/main/scala/resources/weapons.json").getLines().mkString("\n")
    decode[GameData](jsonStr) match {
      case Right(data) => data
      case Left(error) =>
        throw new RuntimeException(s"Failed to parse JSON: $error")
    }
  }

  def getGameData: GameData = gameData
}
