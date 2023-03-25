package io.github.muqhc.skygui

import io.github.muqhc.skygui.component.SkyComponent
import io.github.muqhc.skygui.event.SkyDisplayInteractEvent
import io.github.muqhc.skygui.util.Point
import io.github.muqhc.skygui.util.SkyRayTraceResult
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.util.Vector
import kotlin.math.PI
import kotlin.math.cos

interface SkyDisplay {
    val components: MutableList<SkyComponent>

    val normalVector: Vector
    val displayXAxis: Vector get() = normalVector.clone().rotateAroundY(3*PI/2).setY(0).normalize()
    val displayYAxis: Vector get() = displayXAxis.clone().rotateAroundAxis(normalVector,3*PI/2).normalize()
    val location: Location

    private fun loc() = location.toVector().clone()
    private fun nor() = normalVector.clone()

    fun getLocationOnDisplay(locationOnSpace: Vector): Point {
        val roc = { locationOnSpace.clone() }

        val angX = roc().subtract(loc()).angle(displayXAxis)
        val angY = roc().subtract(loc()).angle(displayYAxis)
        val dis = roc().subtract(loc()).length()

        return Point(
            cos(angX) * dis,
            cos(angY) * dis
        )
    }

    fun getLocationOnSpace(locationOnDisplay: Point): Vector {
        val (p2x,p2y) = locationOnDisplay
        val vx = displayXAxis.multiply(p2x)
        val vy = displayYAxis.multiply(p2y)
        return loc().add(vx).add(vy)
    }

    fun render() {
        components.forEach { it.render(this) }
    }

    fun rayTrace(
        posX: Double, posY: Double, posZ: Double,
        dirX: Double, dirY: Double, dirZ: Double,
    ): SkyRayTraceResult = object : SkyRayTraceResult {
        override val hitLocation: Location = Location(this@SkyDisplay.location.world,0.0,0.0,0.0).apply {
            val pos = { Vector(posX, posY, posZ) }
            val dir = { Vector(dirX, dirY, dirZ) }

            val incl = { nor().dot(loc().subtract(pos())) / nor().dot(dir()) }

            val result = { pos().add(dir().multiply(incl())) }

            result().let {
                x = it.x
                y = it.y
                z = it.z
            }
        }
        override val hitLocationOnDisplay: Point = getLocationOnDisplay(hitLocation.toVector())
        override val isFaceToFace: Boolean = normalVector.angle(Vector(dirX, dirY, dirZ)) > (PI/2)
    }
    
    fun rayTrace(location: Vector, direction: Vector) = rayTrace(
        location.x,
        location.y,
        location.z,
        direction.x,
        direction.y,
        direction.z,
    )

    fun rayTrace(location: Location, direction: Vector) = rayTrace(location.toVector(), direction)

    fun rayTrace(locationWithDirection: Location) = rayTrace(locationWithDirection,locationWithDirection.direction)

    fun click(event: SkyDisplayInteractEvent) {
        if (event.traceResult.isFaceToFace) components.forEach {
            if (it.isPointIn(event.traceResult.hitLocationOnDisplay)) it.click(event)
        }
    }


}