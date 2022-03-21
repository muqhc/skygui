package io.github.muqhc.skygui.component

import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.event.SkyDisplayInteractEvent
import io.github.muqhc.skygui.util.Point
import io.github.muqhc.skygui.util.Vector2D
import org.bukkit.Particle
import org.bukkit.WorldCreator

interface SkyComponent {
    val point1: Point
    val point2: Point

    fun click(event: SkyDisplayInteractEvent) {
        if (isPointIn(event.traceResult.hitLocationOnDisplay)) onClicked(event)
    }

    fun onClicked(event: SkyDisplayInteractEvent) {}

    fun render(display: SkyDisplay)

    fun isPointIn(target: Point) =
        (point1.x >= target.x) and (point2.x <= target.x) and
                (point1.y >= target.y) and (point2.y <= target.y)
}