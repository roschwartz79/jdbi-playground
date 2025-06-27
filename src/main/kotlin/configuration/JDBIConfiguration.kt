package configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import java.util.Properties

/**
 * JDBIConfiguration class to configure JDBI with PostgreSQL database.
 *
 * This class provides methods to create a Jdbi instance using either properties or a HikariCP datasource.
 */
class JDBIConfiguration {

  /**
   * Configures JDBI with a PostgreSQL database connection using properties
   */
  fun configure(): Jdbi {
    val properties = Properties()
    properties.setProperty("user", "jdbi_user")
    properties.setProperty("password", "password")
    val jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/jdbi_playground", properties)
    jdbi.installPlugin(KotlinPlugin())
    return jdbi
  }

  /**
   * Configures JDBI with a PostgreSQL database connection using HikariCP
   */
  fun configureWithDatasource(): Jdbi {
    val config = HikariConfig()
    config.jdbcUrl = "jdbc:postgresql://localhost:5432/jdbi_playground"
    config.username = "jdbi_user"
    config.password = "password"
    val dataSource = HikariDataSource(config)
    return Jdbi.create(dataSource)
  }
}