import configuration.JDBIConfiguration
import dto.TeamMember
import org.jdbi.v3.core.Jdbi
import org.slf4j.LoggerFactory
import repository.BasicJDBIRepository
import table.JDBITableCreator

fun main(args: Array<String>) {

  val logger = LoggerFactory.getLogger("Main")

  val jdbi = configuration()
  val basicJdbiRepository = BasicJDBIRepository(jdbi = jdbi)

  val tm1 = TeamMember(
    id = 1,
    name = "Rob",
    funFact = "Nothing special"
  )
  val tm2 = TeamMember(
    id = 2,
    name = "Alex",
    funFact = "Supervisor"
  )

  basicJdbiRepository.addTeamMember(tm1)
  basicJdbiRepository.addTeamMember(tm2)

  val teamMembers = basicJdbiRepository.getTeamMembers()

  logger.info("Team Members: $teamMembers")

  basicJdbiRepository.removeTeamMember(teamMembers.find { it.name == tm1.name }!!)

  val teamMembersAfterRemoval = basicJdbiRepository.getTeamMembers()

  logger.info("Team Members after removal: $teamMembersAfterRemoval")

  basicJdbiRepository.updateTeamMember(tm2.copy(funFact = "Updated fun fact"))

  val updatedTeamMembers = basicJdbiRepository.getTeamMembers()

  logger.info("Updated Team Members: $updatedTeamMembers")
}

fun configuration(): Jdbi {

  val jdbiConfiguration = JDBIConfiguration()

  val jdbi = jdbiConfiguration.configure()

  val jdbiTableCreator = JDBITableCreator(jdbi = jdbi)

  jdbiTableCreator.createTable()

  return jdbi
}
