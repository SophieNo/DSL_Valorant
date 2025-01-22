package strategy.map

object NaturalLanguageInterpreter {
  def interpret(question: String): String = {
    // Expressions régulières pour les différents cas
    val compositionPattern = "composition pour tel (\\w+)(?: en (attaque|défense))?(?: sachant (.+))?".r
    val buyingAdviceFullPattern = "j'ai (\\d+) crédits avec (\\w+) sur (\\w+) en (\\w+), (\\w+), avec (.+)".r
    val buyingAdvicePartialPattern1 = "j'ai (\\d+) crédits avec (\\w+) sur (\\w+) en (\\w+), (\\w+)".r
    val buyingAdvicePartialPattern2 = "j'ai (\\d+) crédits avec (\\w+) sur (\\w+) en (\\w+)".r
    val buyingAdvicePartialPattern3 = "j'ai (\\d+) crédits avec (\\w+) sur (\\w+)".r
    val buyingAdvicePartialPattern4 = "j'ai (\\d+) crédits avec (\\w+)".r
    val strategyPattern = "donne moi une stratégie pour (\\w+) sur (\\w+)".r

    question match {
      case compositionPattern(map, role, agents) =>
        val rolePart = if (role != null) s":$role" else ""
        val agentsPart = if (agents != null) s":$agents" else ""
        s"composition:$map$rolePart$agentsPart"

      case buyingAdviceFullPattern(credits, agent, map, roundType, playstyle, equipment) =>
        s"buyingadvice:$credits:$agent:$map:$roundType:$playstyle:$equipment"

      case buyingAdvicePartialPattern1(credits, agent, map, roundType, playstyle) =>
        s"buyingadvice:$credits:$agent:$map:$roundType:$playstyle"

      case buyingAdvicePartialPattern2(credits, agent, map, roundType) =>
        s"buyingadvice:$credits:$agent:$map:$roundType"

      case buyingAdvicePartialPattern3(credits, agent, map) =>
        s"buyingadvice:$credits:$agent:$map"

      case buyingAdvicePartialPattern4(credits, agent) =>
        s"buyingadvice:$credits:$agent"

      case strategyPattern(agent, map) =>
        s"strategy:$map:$agent"

      case _ =>
        "unknown"
    }
  }
}
