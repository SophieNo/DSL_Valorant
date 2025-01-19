file:///C:/Users/willi/Desktop/scala_projet_valorant/Projet-Scala/src/main/scala/buyOptimizer.worksheet.sc
### java.lang.AssertionError: assertion failed: denotation object language invalid in run 3. ValidFor: Period(4.1-2)

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file:///C:/Users/willi/Desktop/scala_projet_valorant/Projet-Scala/src/main/scala/buyOptimizer.worksheet.sc
text:
```scala
object worksheet{
  import valorantdsl.* 
  import strategy.GameDataParser
  import enums.*
  
  Character.Jett
    .playWith(3000.credits)
    .onMap(Map.Ascent)
    .inRound(RoundType.FullBuy)
    .preferredAs(Playstyle.Aggressive)
    .owned(Set(Subtype.Shield, Subtype.Ability))
    .build(3.items)
  
  
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.Denotations$SingleDenotation.updateValidity(Denotations.scala:718)
	dotty.tools.dotc.core.Denotations$SingleDenotation.bringForward(Denotations.scala:743)
	dotty.tools.dotc.core.Denotations$SingleDenotation.toNewRun$1(Denotations.scala:800)
	dotty.tools.dotc.core.Denotations$SingleDenotation.current(Denotations.scala:871)
	dotty.tools.dotc.core.Symbols$Symbol.recomputeDenot(Symbols.scala:117)
	dotty.tools.dotc.core.Symbols$Symbol.computeDenot(Symbols.scala:111)
	dotty.tools.dotc.core.Symbols$Symbol.denot(Symbols.scala:104)
	dotty.tools.dotc.core.Symbols$.toDenot(Symbols.scala:504)
	dotty.tools.dotc.typer.Checking.checkLegalImportPath(Checking.scala:983)
	dotty.tools.dotc.typer.Checking.checkLegalImportPath$(Checking.scala:851)
	dotty.tools.dotc.typer.Typer.checkLegalImportPath(Typer.scala:116)
	dotty.tools.dotc.typer.Typer.typedImport(Typer.scala:2856)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3128)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3197)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3267)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3271)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3283)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3339)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2736)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:3104)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3108)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3196)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3267)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3271)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3293)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3339)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2879)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3149)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3197)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3267)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3271)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3382)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:45)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:458)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:51)
	dotty.tools.dotc.typer.TyperPhase.$anonfun$4(TyperPhase.scala:97)
	scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:479)
	scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:583)
	scala.collection.immutable.List.prependedAll(List.scala:152)
	scala.collection.immutable.List$.from(List.scala:685)
	scala.collection.immutable.List$.from(List.scala:682)
	scala.collection.IterableOps$WithFilter.map(Iterable.scala:900)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:96)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:315)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1323)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:308)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:349)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:358)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:69)
	dotty.tools.dotc.Run.compileUnits(Run.scala:358)
	dotty.tools.dotc.Run.compileSources(Run.scala:261)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:161)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:45)
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:345)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:88)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: denotation object language invalid in run 3. ValidFor: Period(4.1-2)