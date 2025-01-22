import valorantdsl.* 
import enums.*

Character.Jett
  .playWith(1500.credits)
  .onMap(Map.Ascent)
  .inRound(RoundType.FullBuy)
  .preferredAs(Playstyle.Aggressive)
  .build(5.items)
