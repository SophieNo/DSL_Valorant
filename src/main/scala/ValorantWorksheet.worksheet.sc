import assistant.ValorantAssistant

// Posez simplement vos questions en utilisant les formats suivants :

// Utilisez ValorantAssistant.ask("") et taper votre question 

// 1. **Composition d'équipe** :
// Format : "composition pour tel [nom de carte] en [attaque/défense] sachant [agent1, agent2, ...]"
// Exemple :
ValorantAssistant.ask("composition pour tel Haven en attaque sachant Jett, Cypher")
// -> Réponse : Suggestions d'agents pour la carte et le rôle spécifiés.

// 2. **Conseils d'achat** :
// Format : "J'ai [montant de crédits] crédits avec [nom de l'agent] sur [nom de carte] en [type de round], [style de jeu], avec [équipement]"
// Exemple :
ValorantAssistant.ask("J'ai 12000 crédits avec Viper sur Ascent en FullBuy, Aggressive, avec Shield")
// -> Réponse : Combinaison d'achats recommandés pour l'agent et les circonstances spécifiés.

// Formats partiels acceptés pour les conseils d'achat :
// - Sans "style de jeu" ni "équipement" : "j'ai [montant de crédits] crédits avec [nom de l'agent] sur [nom de carte] en [type de round]"
// - Sans "type de round" ni "carte" : "j'ai [montant de crédits] crédits avec [nom de l'agent]"
// Exemple :
ValorantAssistant.ask("j'ai 12000 crédits avec Viper sur Ascent en FullBuy, Defensive")
ValorantAssistant.ask("j'ai 12000 crédits avec Viper sur Ascent en Eco")
ValorantAssistant.ask("j'ai 12000 crédits avec Jett sur Ascent")
ValorantAssistant.ask("j'ai 12000 crédits avec Omen")

// 3. **Génération de stratégie** :
// Format : "donne moi une stratégie pour [nom de l'agent] sur [nom de carte]"
// Exemple :
ValorantAssistant.ask("donne moi une stratégie pour Jett sur Haven")
ValorantAssistant.ask("donne moi une stratégie pour Brimstone sur Bind")
