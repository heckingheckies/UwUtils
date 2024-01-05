package dev.smuggies.uwutils.minecraft

import gg.flyte.twilight.Twilight
import gg.flyte.twilight.event.event
import gg.flyte.twilight.twilight
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: CutePlugin

val instance: CutePlugin
  get() = plugin

abstract class CutePlugin : JavaPlugin() {

  open fun onPluginEnable() { val twilight: Twilight = twilight(instance) }

  open fun onPluginDisable() { event<PluginDisableEvent> {} }

  final override fun onLoad() {
    plugin = this
    onPluginEnable()
  }

  final override fun onDisable() { onPluginDisable() }
}