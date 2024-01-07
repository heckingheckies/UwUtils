plugins {
  kotlin("jvm") version "1.9.0"
  application
  id("maven-publish")
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

group = "dev.smuggies"
version = "1.0"

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.flyte.gg/releases")
  maven("https://jitpack.io/")
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT") {
    // Explicitly specify the version here to avoid any ambiguity
    version {
      strictly("1.20.4-R0.1-SNAPSHOT")
    }
  }
  implementation("gg.flyte:twilight:1.0.33")

  implementation("com.github.Revxrsal.Lamp:common:3.1.7")
  implementation("com.github.Revxrsal.Lamp:bukkit:3.1.7")
  implementation("com.moandjiezana.toml:toml4j:0.7.2")
}

tasks {
  shadowJar {
    relocate("dev.triumphteam.gui", "dev.smuggies.uwutils.UwUtils.gui")
  }
}

tasks.test {
  useJUnitPlatform()
}

application {
  mainClass.set("CutePluginKt")
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