package strategy.AgentFolder

case class Agent(
                  name: String,
                  role: String, // Duelist, Sentinel, Controller, Initiator
                  abilities: List[String]
                )
