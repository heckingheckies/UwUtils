package dev.smuggies.uwutils.minecraft

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
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
 * An objectively better and cuter version of [SuspendingJavaPlugin] :3
 *
 * Automatically adds an [instance] that can be used in other projects.
 */
abstract class CutePlugin : SuspendingJavaPlugin() {

  /**
   * This function overrides [onEnable] so we can access the [instance].
   */
  open suspend fun onPluginEnable() {}

  /**
   * This function overrides [onDisable] so we can access the [instance].
   */
  open suspend fun onPluginDisable() {}

  /**
   * Overrides the [onEnableAsync] function for suspension.
   *
   * This function is called when the plugin is loaded.
   * It sets the [plugin] variable to [CutePlugin] and calls the [onPluginEnable] function.
   */
  final override suspend fun onLoadAsync() {
    plugin = this
    onPluginEnable()
  }

  /**
   * Overrides the [onDisableAsync] function for suspension.
   *
   * This function is called when the plugin is disabled.
   * it simply calls the [onPluginDisable] method. The reason for this is to allow
   * subclasses to override this method to perform additional actions when the plugin
   * is disabled.
   *
   * Personally, I use the `PluginDisableEvent` instead, but this is here either way.
   */
  final override suspend fun onDisableAsync() { onPluginDisable() }
}