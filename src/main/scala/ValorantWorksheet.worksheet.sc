import valorant.ValorantDSL._

// Création des joueurs
val jett = player("Jett", 200)
val omen = player("Omen", 2500)
val sova = player("Sova", 1500)

// Création de l'équipe
val myTeam = team(jett, omen, sova)

// Recommandations d'achat
val recommendations = recommendForTeam(myTeam)

// Affichage des recommandations
println(displayRecommendations(recommendations))
