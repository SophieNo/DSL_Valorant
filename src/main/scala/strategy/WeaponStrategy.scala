package strategy

import valorantdsl.PlayerSetup


object Recommender {
  def recommendWeapon(playerSetup: PlayerSetup): String = {
    val gameData = GameDataParser.getGameData

    val suitableWeapons = gameData.weapons.filter { weapon =>
  val conditions = weapon.conditions

  val roleMatches = playerSetup.role match {
    case Some(role) => weapon.recommendedRoles.contains(role) || weapon.recommendedRoles.contains("All")
    case None       => true
  }

  val creditsMatch = conditions.creditsMin.forall(playerSetup.credits >= _)

  val mapMatch = playerSetup.map match {
    case Some(map) =>
      conditions.maps match {
        case Some(maps) => maps.contains(map) || maps.contains("All")
        case None       => true
      }
    case None => true
  }

  val roundTypeMatch = playerSetup.roundType match {
    case Some(roundType) =>
      conditions.roundType match {
        case Some(roundTypes) => roundTypes.contains(roundType)
        case None             => true
      }
    case None => true
  }

  val styleMatch = playerSetup.preferredStyle match {
    case Some(style) => style == conditions.preferredStyle.getOrElse("")
    case None        => true
  }

  roleMatches && creditsMatch && mapMatch && roundTypeMatch && styleMatch
}




    suitableWeapons.headOption match {
      case Some(weapon) =>
        s"""Recommended Weapon: ${weapon.name}
           |Reason: It's suitable for your role (${playerSetup.role}), available credits (${playerSetup.credits}),
           |map (${playerSetup.map}), and round type (${playerSetup.roundType}).
           |Damage: Head = ${weapon.damage.head}, Body = ${weapon.damage.body}, Legs = ${weapon.damage.legs}.
           |Effective Range: ${weapon.effectiveRange}.
           |Cost: ${weapon.cost} credits.
         """.stripMargin

      case None =>
        "No suitable weapon found for the given setup. Try adjusting your strategy or credits."
    }
  }
}
