package strategy.weapon

import strategy.weapon.{Damage, Weapon, WeaponConditions}

case class Damage(head: Option[Int] = None, body: Option[Int] = None, legs: Option[Int] = None)

case class WeaponConditions(
  creditsMin: Option[Int] = None,
  roundType: Option[List[String]] = None,
  maps: Option[List[String]] = None,
  preferredStyle: Option[String] = None
)

case class Weapon(
  name: String,
  `type`: String,
  subtype: String,
  cost: Int,
  damage: Option[Damage] = None,
  fireRate: Option[Double] = None,
  effectiveRange: Option[String] = None,
  protection: Option[Int] = None,
  character: Option[String] = None,
  description: Option[String] = None,
  recommendedRoles: List[String] = List("All"),
  conditions: WeaponConditions
)

case class GameData(weapons: List[Weapon])