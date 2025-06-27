plugins {
  kotlin("jvm") version "2.1.21"
  id("application")
}

group = "com.schwartz"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  // JDBI
  implementation("org.jdbi:jdbi3-core:3.49.5")
  implementation("org.jdbi:jdbi3-kotlin:3.43.0")
  implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.43.0")

  // HikariCP for connection pooling
  implementation("com.zaxxer:HikariCP:5.1.0")

  // Kotlin reflect for JDBI
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  // PostgreSQL driver
  implementation("org.postgresql:postgresql:42.7.3")

  // SLF4J for logging
  implementation("org.slf4j:slf4j-api:2.0.13")
  implementation("org.slf4j:slf4j-simple:2.0.13")

  // kotlin test
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(17)
}
application {
  mainClass = "MainKt"
}