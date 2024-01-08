package dev.smuggies.uwutils.minecraft.util.extensions

import org.bukkit.potion.PotionEffect

/**
 * Checks if a [PotionEffect] is harmful.
 * @return True if the effect is harmful, false if it is not.
 */
fun PotionEffect.isHarmful(): Boolean {
  return when (type.name.uppercase()) {
    "BLINDNESS", "CONFUSION", "HARM", "HUNGER", "POISON", "SLOW", "SLOW_DIGGING", "WEAKNESS", "WITHER" -> true
    else -> false
  }
}