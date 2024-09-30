package dev.smuggies.uwutils.minecraft.util.regions

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import kotlin.math.max
import kotlin.math.min

@Suppress("unused", "MemberVisibilityCanBePrivate")
data class Region(
  private val point1: Location,
  private val point2: Location
) {
  private val xRange: IntRange = min(point1.blockX, point2.blockX)..max(point1.blockX, point2.blockX)
  private val yRange: IntRange = min(point1.blockY, point2.blockY)..max(point1.blockY, point2.blockY)
  private val zRange: IntRange = min(point1.blockZ, point2.blockZ)..max(point1.blockZ, point2.blockZ)
  private val world: World = point1.world

  val blockList: Iterator<Block>
    get() = sequence {
      for (x in xRange) {
        for (y in yRange) { for (z in zRange) yield(world.getBlockAt(x, y, z)) }
      }
    }.iterator()

  val center: Location
    get() = Location(
      world,
      xRange.average(),
      yRange.average(),
      zRange.average()
    )

  val distance: Double
    get() = point1.distance(point2)

  val distanceSquared: Double
    get() = point1.distanceSquared(point2)

  val totalBlockSize: Int
    get() = xRange.count() * yRange.count() * zRange.count()

  fun Location.isIn(): Boolean = world == world && blockX in xRange && blockY in yRange && blockZ in zRange

  fun Player.isIn(): Boolean = location.isIn()

  fun isInWithMargin(location: Location, margin: Double): Boolean = location.world == world && location.x in (xRange.first - margin)..(xRange.last + margin) && location.y in (yRange.first - margin)..(yRange.last + margin) && location.z in (zRange.first - margin)..(zRange.last + margin)
}