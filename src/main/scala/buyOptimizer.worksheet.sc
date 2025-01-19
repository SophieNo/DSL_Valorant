import valorantdsl.* 
import strategy.GameDataParser
import enums.*

Character.Jett
  .playWith(2000.credits)
  .onMap(Map.Ascent)
  .inRound(RoundType.FullBuy)
  .preferredAs(Playstyle.Aggressive)
  .owned(Set(Subtype.Shield))
  .build(3.items)
