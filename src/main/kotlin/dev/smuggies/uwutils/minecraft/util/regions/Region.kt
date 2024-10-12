package dev.smuggies.uwutils.minecraft.util.regions

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random.Default.nextInt

/**
 * A data class representing a [Region] in the [World].
 *
 * @param point1 The first corner of the [Region].
 * @param point2 The second corner of the [Region].
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
data class Region(
  /**
   * The first corner of the [Region].
   */
  private val point1: Location,
  /**
   * The second corner of the [Region].
   */
  private val point2: Location
) {
  /**
   * The x range of the [Region].
   */
  private val xRange: IntRange = min(point1.blockX, point2.blockX)..max(point1.blockX, point2.blockX)
  /**
   * The y range of the [Region].
   */
  private val yRange: IntRange = min(point1.blockY, point2.blockY)..max(point1.blockY, point2.blockY)
  /**
   * The z range of the [Region].
   */
  private val zRange: IntRange = min(point1.blockZ, point2.blockZ)..max(point1.blockZ, point2.blockZ)
  /**
   * The world of the [Region].
   */
  private val world: World = point1.world

  /**
   * Iterates over the blocks in the [Region].
   */
  val blockList: Iterator<Block>
    get() = sequence {
      for (x in xRange) {
        for (y in yRange) { for (z in zRange) yield(world.getBlockAt(x, y, z)) }
      }
    }.iterator()

  /**
   * The center of the [Region].
   */
  val center: Location
    get() = Location(
      world,
      xRange.average(),
      yRange.average(),
      zRange.average()
    )

  /**
   * The distance of the [Region].
   */
  val distance: Double
    get() = point1.distance(point2)

  /**
   * The squared distance of the [Region].
   */
  val distanceSquared: Double
    get() = point1.distanceSquared(point2)

  /**
   * The total block size of the [Region].
   */
  val totalBlockSize: Int
    get() = xRange.count() * yRange.count() * zRange.count()

  /**
   * Checks if a [Location] is in the [Region].
   *
   * @param location The [Location] you want to check.
   *
   * @return If the [Location] is in the [Region].
   */
  fun isIn(location: Location): Boolean = location.world == world && location.blockX in xRange && location.blockY in yRange && location.blockZ in zRange

  /**
   * Checks if a [Player] is in the [Region].
   *
   * @param player The [Player] you want to check.
   *
   * @return If the [Player] is in the [Region].
   */
  fun isIn(player: Player): Boolean = isIn(player.location)

  /**
   * Checks if a [Location] is in the [Region] with a [margin].
   *
   * @param location The location you want to check.
   * @param margin The margin you want to use.
   *
   * @return If the location is in the region with the margin.
   */
  fun isInWithMargin(location: Location, margin: Double): Boolean = location.world == world && location.x in (xRange.first - margin)..(xRange.last + margin) && location.y in (yRange.first - margin)..(yRange.last + margin) && location.z in (zRange.first - margin)..(zRange.last + margin)

  /**
   * Gets a random [Location] within the [Region].
   *
   * @return A random [Location] within the [Region].
   */
  fun randomLocation(): Location {
    val x = nextInt(xRange.first, xRange.last + 1)
    val y = nextInt(yRange.first, yRange.last + 1)
    val z = nextInt(zRange.first, zRange.last + 1)
    return Location(world, x.toDouble(), y.toDouble(), z.toDouble())
  }

  /**
   * Gets a random [Location] within the [Region] while keeping the [yRange] on the ground.
   *
   * @return A random [Location] within the [Region].
   */
  fun randomGroundedLocation(): Location {
    val x = nextInt(xRange.first, xRange.last + 1)
    val z = nextInt(zRange.first, zRange.last + 1)
    return Location(world, x.toDouble(), yRange.first.toDouble(), z.toDouble())
  }
}