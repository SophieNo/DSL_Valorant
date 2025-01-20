
case class Map(
                name: String,
                description: String,
                advantages: String,
                disadvantages: String
              )


object Main extends App {
  val maps = MapDataParser.getMapData

  maps.foreach { map =>
    println(s"Nom: ${map.name}")
    println(s"Description: ${map.description}")
    println(s"Avantages: ${map.advantages}")
    println(s"Désavantages: ${map.disadvantages}")
    println("-" * 50)
  }
}