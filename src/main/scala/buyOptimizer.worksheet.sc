import valorantdsl.* 
import strategy.GameDataParser
import enums.*

Character.Jett
  .playWith(3000.credits)
  .onMap(Map.Ascent)
  .inRound(RoundType.FullBuy)
  .preferredAs(Playstyle.Aggressive)
  .build(3)