package strategy

case class Damage(head: Int, body: Int, legs: Int)
case class WeaponConditions(
  creditsMin: Option[Int] = None,
  creditsMax: Option[Int] = None,
  roundType: Option[List[String]] = None,
  maps: Option[List[String]] = None,
  preferredStyle: Option[String] = None
)
case class Weapon(
  name: String,
  `type`: String,
  cost: Int,
  damage: Damage,
  fireRate: Double,
  effectiveRange: String,
  recommendedRoles: List[String],
  conditions: WeaponConditions
)
case class GameData(weapons: List[Weapon])
