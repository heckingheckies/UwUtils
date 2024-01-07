import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.9.0"
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
  java
  id("maven-publish")
}

group = "dev.smuggies"
version = "1.0"

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
  maven { url = uri("https://jitpack.io") }
}

dependencies {
  implementation(kotlin("stdlib"))
  compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")

  implementation("com.github.Revxrsal.Lamp:common:3.1.7")
  implementation("com.github.Revxrsal.Lamp:bukkit:3.1.7")
  implementation("com.moandjiezana.toml:toml4j:0.7.2")
}

tasks.test { useJUnitPlatform() }

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

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }

application { mainClass.set("MinethologyKt") }