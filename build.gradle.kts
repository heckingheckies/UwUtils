plugins {
  kotlin("jvm") version "2.0.20"
  id("maven-publish")
}

group = "dev.smuggies"
version = "1.0"

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://jitpack.io")
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20")
  compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

val targetJavaVersion = 21

java {
  val javaVersion = JavaVersion.toVersion(targetJavaVersion)
  if (JavaVersion.current() < javaVersion) toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
}

tasks.withType<JavaCompile> {
  if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) options.release.set(targetJavaVersion)
}

tasks.named<ProcessResources>("processResources") {
  val props = mapOf("version" to version)
  inputs.properties(props)
  filteringCharset = "UTF-8"
  filesMatching("plugin.yml") { expand(props) }
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      groupId = "dev.smuggies"
      artifactId = "UwUtils"
      version = "1.0"
      from(components["java"])
    }
  }
}

kotlin { jvmToolchain(21) }