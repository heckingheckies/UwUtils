import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.9.0"
  application
  id("maven-publish")
  id("com.github.johnrengelman.shadow") version "7.1.2"
  id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
  java
}

group = "dev.smuggies"
version = "1.0"

val jarName = "UwUtils"
val dirPaths: List<String> = listOf("C:/servers/Skypolis/plugins")

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.flyte.gg/releases")
  maven("https://jitpack.io/")
}

dependencies {
  implementation(kotlin("stdlib"))
  compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
  implementation("gg.flyte:twilight:1.0.33")
  implementation("com.github.Tatsuwuki:uwutils:40c4187b19")

  implementation("com.github.Revxrsal.Lamp:common:3.1.7")
  implementation("com.github.Revxrsal.Lamp:bukkit:3.1.7")
  implementation("com.moandjiezana.toml:toml4j:0.7.2")
}

tasks {
  shadowJar { relocate("dev.triumphteam.gui", "dev.smuggies.uwutils.UwUtils.gui") }
}

tasks.register("copyToAll") {
  group = "UwUtils"
  description = "Builds the UwUtils Jar"
  dependsOn(tasks.shadowJar)
  doLast {
    getTheDir().forEach { dest ->
      println("Built in: $dest")
      copy {
        from(tasks.shadowJar.flatMap { it.archiveFile })
        into(dest)
        rename { "$jarName.jar" }
      }
    }
  }
}

fun getTheDir(): List<File> { return dirPaths.map { File(it) }.filter { it.exists() } }

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }

application { mainClass.set("CutePluginKt") }

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

bukkit {
  name = "UwUtils"
  version = "1.0"
  main = "dev.smuggies.uwutils.minecraft.CutePlugin"
  apiVersion = "1.20"
}