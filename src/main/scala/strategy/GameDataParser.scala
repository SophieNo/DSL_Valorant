package strategy

import io.circe.generic.auto._
import io.circe.parser.decode
import scala.io.Source

object GameDataParser {
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
