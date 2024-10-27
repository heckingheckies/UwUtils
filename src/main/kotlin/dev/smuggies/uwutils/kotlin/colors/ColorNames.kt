package dev.smuggies.uwutils.kotlin.colors

import com.github.ajalt.colormath.model.LAB
import com.github.ajalt.colormath.model.RGB
import kotlin.math.abs

/**
 * Finds the name of the closest color to a given color in the [CIELAB color space](https://en.wikipedia.org/wiki/CIELAB_color_space).
 *
 * @param colorNames The list of [CachedColor]'s to search the closest color to.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ColorNames(colorNames: List<CachedColor>) {
  private val kdTree: KDNode? = buildKDTree(colorNames.map { Triple(it.lightness, it.aComponent, it.bComponent) to it }, 0)

  /**
   * The name of the closest color to the given `hex` color.
   *
   * @param hex The `hex` color to find the closest color to, e.g. `#FF0000`, `FF0000`.
   *
   * @return The name of the closest color to the given `hex` color.
   */
  fun name(hex: String): String = findClosestColor(hex).name

  /**
   * The name of the closest color to the given [RGB] color.
   *
   * @param r The red component of the color, from `0` to `255`.
   * @param g The green component of the color, from `0` to `255`.
   * @param b The blue component of the color, from `0` to `255`.
   *
   * @return The name of the closest color to the given [RGB] color.
   */
  fun name(r: Int, g: Int, b: Int): String = findClosestColor(r, g, b).name

  /**
   * The name of the closest color to the given [LAB] color.
   *
   * @param lab The [LAB] color to find the closest color to.
   *
   * @return The name of the closest color to the given [LAB] color.
   */
  fun name(lab: LAB): String = findClosestColor(lab).name

  /**
   * Finds the closest color to the given `hex` color.
   *
   * @param hex The `hex` color to find the closest color to, e.g. `#FF0000`, `FF0000`.
   *
   * @return The closest color to the given `hex` color.
   */
  private fun findClosestColor(hex: String): CachedColor = findClosestColor(RGB(hex).toLAB())

  /**
   * Finds the closest color to the given [RGB] color.
   *
   * @param r The red component of the color, from `0` to `255`.
   * @param g The green component of the color, from `0` to `255`.
   * @param b The blue component of the color, from `0` to `255`.
   *
   * @return The closest color to the given [RGB] color.
   */
  fun findClosestColor(r: Int, g: Int, b: Int): CachedColor = findClosestColor(RGB(r / 255f, g / 255f, b / 255f).toLAB())

  /**
   * Finds the closest color to the given [LAB] color.
   *
   * @param lab The [LAB] color to find the closest color to.
   *
   * @return The closest color to the given [LAB] color.
   */
  fun findClosestColor(lab: LAB): CachedColor {
    val point = Triple(lab.l, lab.a, lab.b)
    return findNearest(kdTree, point, 0)?.second ?: throw IllegalStateException("No colors found?")
  }

  /**
   * A node in the KD tree used to find the closest color to a given color.
   *
   * @param point The point in the [CIELAB color space](https://en.wikipedia.org/wiki/CIELAB_color_space) this node represents.
   * @param color The [CachedColor] this node represents.
   * @param left The [left] child of this node.
   * @param right The [right] child of this node.
   */
  private data class KDNode(
    val point: Triple<Float, Float, Float>,
    val color: CachedColor,
    val left: KDNode?,
    val right: KDNode?
  )

  /**
   * Build a KD tree from a list of [points].
   *
   * @param points The [points] to build the KD tree from.
   * @param depth The [depth] of the current node in the tree.
   *
   * @return The root node of the KD tree.
   */
  private fun buildKDTree(
    points: List<Pair<Triple<Float, Float, Float>, CachedColor>>,
    depth: Int
  ): KDNode? {
    if (points.isEmpty()) return null

    val axis = depth % 3
    val sortedPoints = points.sortedBy { it.first[axis] }
    val medianIdx = sortedPoints.size / 2
    val medianPoint = sortedPoints[medianIdx]

    return KDNode(
      medianPoint.first,
      medianPoint.second,
      buildKDTree(sortedPoints.subList(0, medianIdx), depth + 1),
      buildKDTree(sortedPoints.subList(medianIdx + 1, sortedPoints.size), depth + 1)
    )
  }

  /**
   * Find the nearest color to a given [target] point in the KD tree.
   *
   * @param node The current [node] in the KD tree.
   * @param target The [target] point to find the nearest color to.
   * @param depth The [depth] of the current [node] in the tree.
   *
   * @return The nearest color to the [target] point.
   */
  private fun findNearest(
    node: KDNode?,
    target: Triple<Float, Float, Float>,
    depth: Int
  ): Pair<Triple<Float, Float, Float>, CachedColor>? {
    if (node == null) return null

    val axis = depth % 3
    val (nextBranch, oppositeBranch) = if (target[axis] <= node.point[axis]) { node.left to node.right } else { node.right to node.left }

    val nearest = findNearest(nextBranch, target, depth + 1)
    var bestPair = nearest?.takeIf { squaredDeltaE(target, it.first) < squaredDeltaE(target, node.point) } ?: (node.point to node.color)

    val axisDist = abs(target[axis] - node.point[axis])
    if (axisDist * axisDist < squaredDeltaE(target, bestPair.first)) {
      findNearest(oppositeBranch, target, depth + 1)?.let { if (squaredDeltaE(target, it.first) < squaredDeltaE(target, bestPair.first)) bestPair = it }
    }

    return bestPair
  }

  /**
   * Calculate the squared `delta E` between two points in the [CIELAB color space](https://en.wikipedia.org/wiki/CIELAB_color_space).
   *
   * @param p1 The first point.
   * @param p2 The second point.
   *
   * @return The squared `delta E` between the two points.
   */
  private fun squaredDeltaE(
    p1: Triple<Float, Float, Float>,
    p2: Triple<Float, Float, Float>
  ): Float {
    val (dL, da, db) = Triple(p1.first - p2.first, p1.second - p2.second, p1.third - p2.third)
    return dL * dL + da * da + db * db
  }

  private operator fun Triple<Float, Float, Float>.get(index: Int): Float = when (index) {
    0 -> first
    1 -> second
    else -> third
  }
}