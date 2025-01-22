package strategy.weapon

import strategy.weapon.{GameDataParser, Weapon}
import valorantdsl.PlayerSetup
import enums.*

object Recommender {
  def recommend(setup: PlayerSetup, character: Character.Character, topN: Int): Unit = {
    val recommendations = recommendBestCombination(setup, character, topN)
    if (recommendations.isEmpty) {
      println("No suitable combination found within your budget. Try adjusting your strategy or credits.")
    } else {
      println("Recommended Combination:")
      recommendations.foreach { weapon =>
        println(s"""
          |  - ${weapon.name}
          |    Subtype: ${weapon.subtype}
          |    Cost: ${weapon.cost} credits
          |    ${weapon.description.getOrElse("")}
        """.stripMargin)
      }
    }
  }

  private def recommendBestCombination(
    setup: PlayerSetup,
    character: Character.Character,
    topN: Int
  ): List[Weapon] = {
    val gameData = GameDataParser.getGameData

    // Filtrer les armes disponibles
    val availableWeapons = gameData.weapons
      .filterNot(w => setup.ownedItems.contains(w.subtype)) // Exclure les items déjà possédés
      .filter(w => meetsCriteria(w, setup, character))     // Critères supplémentaires

    // Partitionner les armes en catégories
    val (shields, abilities, weapons) = availableWeapons.partition3(
      _.subtype == Subtype.Shield,
      w => w.subtype == Subtype.Ability && w.character.contains(character), // Filtrer strictement par agent
      w => w.subtype == Subtype.Rifle || w.subtype == Subtype.Utility
    )

    optimizeCombination(shields, abilities, weapons, setup.credits).take(topN)
  }

  private def meetsCriteria(
    weapon: Weapon,
    setup: PlayerSetup,
    character: Character.Character
  ): Boolean = {
    val conditions = weapon.conditions

    val isAgentAbility = weapon.subtype match {
      case Subtype.Ability => weapon.character.contains(character)
      case _               => true
    }

    val creditsMatch = conditions.creditsMin.forall(min => setup.credits >= min)
    val mapMatch = conditions.maps.forall(_.isEmpty || setup.map.exists(conditions.maps.get.contains))
    val roundMatch = conditions.roundType.forall(_.contains(setup.roundType.getOrElse(RoundType.FullBuy)))
    val styleMatch = conditions.preferredStyle.forall(style => setup.preferredStyle.contains(style))

    // Vérification stricte de toutes les conditions
    isAgentAbility && creditsMatch && mapMatch && roundMatch && styleMatch
  }

  private def optimizeCombination(
    shields: List[Weapon],
    abilities: List[Weapon],
    weapons: List[Weapon],
    budget: Int
  ): List[Weapon] = {
    val allItems = (shields ++ abilities ++ weapons).sortBy(_.cost)
    var remainingBudget = budget
    var selectedItems = List.empty[Weapon]

    def addItemsByPriority(items: List[Weapon]): Unit = {
      items.foreach { item =>
        if (item.cost <= remainingBudget) {
          selectedItems = selectedItems :+ item
          remainingBudget -= item.cost
        }
      }
    }

    addItemsByPriority(shields.sortBy(-_.protection.getOrElse(0)))
    addItemsByPriority(abilities)
    addItemsByPriority(weapons)

    selectedItems
  }

  implicit class PartitionList[T](list: List[T]) {
    def partition3(
      cond1: T => Boolean,
      cond2: T => Boolean,
      cond3: T => Boolean
    ): (List[T], List[T], List[T]) = {
      val (first, rest) = list.partition(cond1)
      val (second, third) = rest.partition(cond2)
      (first, second, third)
    }
  }
}
