package dev.smuggies.uwutils.minecraft

import org.bukkit.plugin.java.JavaPlugin

/**
 * The [plugin] variable to get the [instance] from.
 */
lateinit var plugin: CutePlugin

/**
 * The [instance] of your very cute [plugin].
 *
 * You can use this [instance] in other projects, without having to make a new [instance] in them.
 */
val instance: CutePlugin
  get() = plugin

/**
 * An objectively better and cuter version of [JavaPlugin] :3
 *
 * Automatically adds an [instance] that can be used in other projects.
 */
abstract class CutePlugin : JavaPlugin() {

  /**
   * This function overrides [onEnable] so we can access the [instance].
   */
  open fun onPluginEnable() {}

  /**
   * This function overrides [onDisable] so we can access the [instance].
   */
  open fun onPluginDisable() {}

  /**
   * Overrides the [onLoad] function.
   *
   * This function is called when the plugin is loaded.
   * It sets the [plugin] variable to [CutePlugin] and calls the [onPluginEnable] function.
   */
  final override fun onLoad() {
    plugin = this
    onPluginEnable()
  }

  /**
   * Overrides the [onDisable] function.
   *
   * This function is called when the plugin is disabled.
   * it simply calls the [onPluginDisable] method. The reason for this is to allow
   * subclasses to override this method to perform additional actions when the plugin
   * is disabled.
   *
   * Personally, I use the `PluginDisableEvent` instead, but this is here either way.
   */
  final override fun onDisable() { onPluginDisable() }
}