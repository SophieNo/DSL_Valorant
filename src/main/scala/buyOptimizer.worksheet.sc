import valorantdsl.* 
import strategy.GameDataParser


"Duelist".playWith(3000.credits).onMap("Ascent").inRound("Eco").preferredAs("").build()

"Duelist".playWith(3000.credits).build()

val gameData = GameDataParser.getGameData.weapons