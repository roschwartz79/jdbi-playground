package dto

import org.jdbi.v3.core.mapper.reflect.ColumnName
import java.util.UUID

/**
 * Represents a team member in the system.
 */
data class TeamMember(
  val id: Long,
  val name: String,
  @ColumnName("fun_fact") val funFact: String? = null,
  val tmId: UUID?
)