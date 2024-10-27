package dev.smuggies.uwutils.minecraft

import org.bukkit.plugin.java.JavaPlugin

/**
 * The [plugin] variable to get the [instance] from.
 */
lateinit var plugin: CutePlugin

/**
 * The [instance] of your very cute [plugin].
 *
 * You can use this [instance] anywhere, without having to initialize a new [instance].
 */
val instance: CutePlugin
  get() = plugin

/**
 * An objectively better and cuter version of [JavaPlugin] :3
 *
 * Automatically initializes an [instance] that can be used anywhere
 * without having to initialize it in your own project.
 */
abstract class CutePlugin : JavaPlugin() {

  /**
   * Overrides [onEnable] so the [instance] can be accessed.
   */
  open fun onPluginEnable() {}

  /**
   * Overrides [onDisable] so the [instance] can be accessed.
   */
  open fun onPluginDisable() {}

  /**
   * Overrides the [onEnable] function.
   *
   * It is called when the [plugin] is loaded.
   * Sets the [plugin] variable to [CutePlugin] and calls the [onPluginEnable] function.
   */
  final override fun onLoad() {
    plugin = this
    onPluginEnable()
  }

  /**
   * Overrides the [onDisable] function.
   *
   * It is called when the [plugin] is disabled.
   * Simply calls the [onPluginDisable] function.
   * The reason for this is to allow
   * subclasses to override this function to perform additional actions when the [plugin]
   * is disabled.
   *
   * Personally, I use the `PluginDisableEvent` instead, but this is here either way.
   */
  final override fun onDisable() = onPluginDisable()
}