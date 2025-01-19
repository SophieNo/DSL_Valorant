import io.circe.generic.auto.*
import io.circe.parser.decode

object MapDataParser {

  // Type alias pour simplifier la déclaration
  type MapData = List[Map]

  // Chargement des données depuis le fichier JSON
  private lazy val mapData: MapData = {
    val jsonStr = scala.io.Source.fromFile("src/main/scala/resources/maps.json").getLines().mkString("\n")
    decode[MapData](jsonStr) match {
      case Right(data) => data
      case Left(error) =>
        throw new RuntimeException(s"Failed to parse JSON: $error")
    }
  }

  // Méthode pour accéder aux données des cartes
  def getMapData: MapData = mapData
}
