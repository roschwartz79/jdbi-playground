package repository

import dto.TeamMember
import org.jdbi.v3.core.Jdbi

/**
 * BasicJDBIRepository class to demonstrate basic JDBI operations.
 */
class BasicJDBIRepository(val jdbi: Jdbi) {

  /**
   * Retrieves all team members from the database
   */
  fun getTeamMembers(): List<TeamMember> {
    return jdbi.withHandle<List<TeamMember>, Exception> { handle ->
      handle.createQuery("SELECT * FROM team_members")
        .mapTo(TeamMember::class.java)
        .list()
    }
  }

  /**
   * Retrieves a team member by their ID
   */
  fun addTeamMember(teamMember: TeamMember) {
    jdbi.withHandle<Unit, Exception> { handle ->
      handle.createUpdate("INSERT INTO team_members (name, fun_fact) VALUES (:name, :funFact) ON CONFLICT (name) DO NOTHING")
        .bind("name", teamMember.name)
        .bind("funFact", teamMember.funFact)
        .execute()
    }
  }

  /**
   * Updates an existing team member in the database
   */
  fun updateTeamMember(teamMember: TeamMember) {
    jdbi.withHandle<Unit, Exception> { handle ->
      handle.createUpdate("UPDATE team_members SET name = :name, fun_fact = :funFact WHERE id = :id")
        .bind("id", teamMember.id)
        .bind("name", teamMember.name)
        .bind("funFact", teamMember.funFact)
        .execute()
    }
  }

  /**
   * Removes a team member from the database
   */
  fun removeTeamMember(teamMember: TeamMember) {
    jdbi.withHandle<Unit, Exception> { handle ->
      handle.createUpdate("DELETE FROM team_members WHERE id = :id")
        .bind("id", teamMember.id)
        .execute()
    }
  }
}