package dev.smuggies.uwutils.kotlin.colors

/**
 * Represents a cached color with values from the [CIELAB color space](https://en.wikipedia.org/wiki/CIELAB_color_space).
 *
 * @param name The [name] of the color.
 *
 * @param lightness The [lightness] of the color.
 * @param aComponent The `A` component of the color.
 * @param bComponent The `B` component of the color.
 */
data class CachedColor(
  val name: String,

  val lightness: Float,
  val aComponent: Float,
  val bComponent: Float
)