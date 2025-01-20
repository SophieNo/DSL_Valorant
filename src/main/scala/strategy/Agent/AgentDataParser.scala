package strategy.Agent

import io.circe.generic.auto._
import io.circe.parser.decode


object AgentDataParser {

  type AgentData = List[Agent]

  private lazy val agentData: AgentData = {
    val jsonStr = scala.io.Source.fromFile("src/main/scala/resources/agents.json").getLines().mkString("\n")
    decode[AgentData](jsonStr) match {
      case Right(data) => data
      case Left(error) =>
        throw new RuntimeException(s"Failed to parse JSON: $error")
    }
  }

  def getAgentData: AgentData = agentData
}
