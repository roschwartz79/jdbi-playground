package table

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi

/**
 * Creates and manages JDBI tables.
 */
class JDBITableCreator(val jdbi: Jdbi) {

  /**
   * Creates the `team_members` table if it does not already exist.
   */
  fun createTable() {
    jdbi.withHandle<Unit, Exception> { handle: Handle ->
      handle.execute(
        "create table IF NOT EXISTS team_members" +
            " (" +
            "id SERIAL PRIMARY KEY," +
            " name VARCHAR(255) NOT NULL UNIQUE," +
            " fun_fact TEXT NULL)"
      )
    }
  }
}