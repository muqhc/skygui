package io.github.muqhc.skygui.component

import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.util.Point
import io.github.muqhc.skygui.util.Vector2D
import org.bukkit.Particle

interface SquareRenderComponent : SkyComponent {
    val renderMaterial: Particle
    fun getParticleData(): Any? = null
    override fun render(display: SkyDisplay) {
        val ep1 = Point(point1.x,point2.y)
        val ep2 = Point(point2.x,point1.y)

        val v1 = Vector2D(point1)
        val v2 = Vector2D(ep1)
        val v3 = Vector2D(point2)
        val v4 = Vector2D(ep2)

        (listOf(v1,v2,v3,v4) zip listOf(v2,v3,v4,v1)).forEach { (from,to) ->
            from.splitBetweenVectorWith(to,from.distance(to).toInt()).forEach {
                val vector = display.getLocationOnSpace(it.toPoint())
                display.location.world
                    .spawnParticle(
                        renderMaterial,
                        vector.toLocation(display.location.world),
                        1,0.0,0.0,0.0,0.0,
                        getParticleData()
                    )
            }
        }
    }
}