plugins {
  kotlin("jvm") version "2.4.10"
  id("maven-publish")
  id("io.papermc.paperweight.userdev") version "2.0.0-beta.22"
  id("de.eldoria.plugin-yml.paper") version "0.9.0"
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
  paperweight.paperDevBundle("26.2.build.+")
  api("com.github.ajalt.colormath:colormath:3.6.0")
}

val targetJavaVersion = 25

java { toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion)) }

tasks.withType<JavaCompile> { options.release.set(targetJavaVersion) }

tasks.named<ProcessResources>("processResources") {
  val props = mapOf("version" to version)
  inputs.properties(props)
  filteringCharset = "UTF-8"
  filesMatching("plugin.yml") { expand(props) }
}

paper {
  name = "UwUtils"
  version = "1.0"
  authors = listOf("heckingheckies")
  main = "dev.smuggies.uwutils.minecraft.CutePlugin"
  apiVersion = "26.2"
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

kotlin { jvmToolchain(targetJavaVersion) }