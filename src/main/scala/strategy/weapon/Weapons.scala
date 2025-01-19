package strategy.weapon
import enums.*

case class Damage(head: Option[Int] = None, body: Option[Int] = None, legs: Option[Int] = None)

case class WeaponConditions(
  creditsMin: Option[Int] = None,
  roundType: Option[List[RoundType.RoundType]] = None,
  maps: Option[List[Map.Map]] = None,
  preferredStyle: Option[Playstyle.Playstyle] = None
)

case class Weapon(
  name: String,
  subtype: Subtype.Subtype,
  cost: Int,
  damage: Option[Damage] = None,
  fireRate: Option[Double] = None,
  effectiveRange: Option[String] = None,
  protection: Option[Int] = None,
  character: Option[Character.Character] = None,
  description: Option[String] = None,
  recommendedRoles: List[Character.Character] = List(Character.Jett),
  conditions: WeaponConditions
)

case class GameData(weapons: List[Weapon])

