package dev.smuggies.uwutils.minecraft

import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: CutePlugin

val instance: CutePlugin
  get() = plugin

abstract class CutePlugin : JavaPlugin() {

  open fun onPluginEnable() {}

  open fun onPluginDisable() {}

  final override fun onLoad() {
    plugin = this
    onPluginEnable()
  }

  final override fun onDisable() { onPluginDisable() }
}