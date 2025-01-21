import strategy.AgentFolder.AgentSuggestion.*


// Chargement des maps pour vérification
val maps = loadMaps()
maps.foreach(map => println(s"Carte : ${map.name}, Description : ${map.description}"))

// Saisie : carte et agents déjà sélectionnés
val mapName = "Bind"
val selectedAgents = List("Jett", "Sage", "Brimstone")

// Suggestions d’agents
val suggestions = suggestAgents(mapName, selectedAgents)

// Affichage des résultats
println("\nSuggestions d'agents :")
suggestions.foreach { agent =>
  println(s"Nom : ${agent.name}, Rôle : ${agent.role}, Capacités : ${agent.abilities.mkString(", ")}")
}
