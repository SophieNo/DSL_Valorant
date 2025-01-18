package strategy

import valorantdsl.PlayerSetup

object Recommender {
  def recommendWeapons(playerSetup: PlayerSetup, agent: Option[String], topN: Int): List[(Weapon, Double, String)] = {
    val gameData = GameDataParser.getGameData

    val scoredWeapons = gameData.weapons.flatMap { weapon =>
      val conditions = weapon.conditions

      val characterScore = agent match {
        case Some(agentName) if weapon.subtype == "Ability" && weapon.character.contains(agentName) => 2.0
        case None if weapon.subtype != "Ability"                                                  => 1.0
        case _                                                                                   => 0.0
      }

      val creditsScore = conditions.creditsMin match {
        case Some(minCredits) if playerSetup.credits >= minCredits =>
          1.0 - math.abs(playerSetup.credits - minCredits).toDouble / playerSetup.credits
        case None => 1.0
        case _    => 0.0
      }

      val mapScore = playerSetup.map match {
        case Some(map) =>
          conditions.maps match {
            case Some(maps) if maps.contains(map.toString) || maps.contains("All") => 1.0
            case _                                                                 => 0.0
          }
        case None => 1.0
      }

      val roundTypeScore = playerSetup.roundType match {
        case Some(roundType) =>
          conditions.roundType match {
            case Some(roundTypes) if roundTypes.contains(roundType.toString) => 1.0
            case _                                                           => 0.0
          }
        case None => 1.0
      }

      val styleMatch = playerSetup.preferredStyle match {
        case Some(style) if weapon.conditions.preferredStyle.contains(style.toString) => 1.0
        case None if weapon.conditions.preferredStyle.isEmpty                         => 1.0
        case _                                                                        => 0.0
      }


      val totalScore = characterScore * 2.0 + creditsScore * 1.5 + mapScore + roundTypeScore + styleMatch

      if (totalScore > 0) {
        val advice = generateAdvice(weapon, playerSetup)
        Some((weapon, totalScore, advice))
      } else None
    }

    scoredWeapons.sortBy(-_._2).take(topN)
  }

  def generateAdvice(weapon: Weapon, playerSetup: PlayerSetup): String = {
    weapon.subtype match {
      case "Rifle" => s"${weapon.name} is a strong choice for long-range engagements. Use its accuracy to control sightlines."
      case "Shield" => s"${weapon.name} increases survivability. Always prioritize shields for aggressive engagements."
      case "Ability" => s"${weapon.name} is specific to ${weapon.character.getOrElse("your agent")}. ${weapon.description}"
      case "Utility" => s"${weapon.name} is versatile. Use it to disrupt enemies or secure site control."
      case _ => s"${weapon.name} offers unique utility. Adapt your strategy accordingly."
    }
  }

  def recommend(playerSetup: PlayerSetup, agent: Option[String], topN: Int): Unit = {
    val recommendations = recommendWeapons(playerSetup, agent, topN)
    if (recommendations.isEmpty) {
      println("No suitable weapons found for the given setup. Try adjusting your strategy or credits.")
    } else {
      println("Recommended Weapons:")
      recommendations.foreach { case (weapon, score, advice) =>
        println(s"""
          |  - ${weapon.name} (Score: ${score.formatted("%.2f")})
          |    Subtype: ${weapon.subtype}
          |    Cost: ${weapon.cost} credits
          |    $advice
        """.stripMargin)
      }
    }
  }
}