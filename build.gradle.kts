import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.9.0"
  application
  java
  id("maven-publish")
}

group = "dev.smuggies"
version = "1.0"

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven { url = uri("https://jitpack.io") }
}

dependencies {
  implementation(kotlin("stdlib"))
  compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
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

application { mainClass.set("UwUtilsKt") }