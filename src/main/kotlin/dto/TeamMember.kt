package dto

import org.jdbi.v3.core.mapper.reflect.ColumnName

/**
 * Represents a team member in the system.
 */
data class TeamMember(
  val id: Long,
  val name: String,
  @ColumnName("fun_fact") val funFact: String? = null
)