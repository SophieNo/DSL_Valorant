package strategy.map

object NaturalLanguageInterpreter {
  def interpret(input: String): String = {
    // Parsing user input for map, team, and intent
    val mapRegex = "(?i)map ([a-zA-Z]+)".r
    val teamRegex = "(?i)(attaque|défense)".r
    val map = mapRegex.findFirstMatchIn(input).map(_.group(1)).getOrElse("unknown")
    val team = teamRegex.findFirstMatchIn(input).map(_.group(1)).getOrElse("unknown")

    if (map != "unknown" && team != "unknown") {
      s"teambuilder for $map in $team"
    } else {
      "Could not understand your input. Please specify the map and team (attaque/défense)."
    }
  }
}