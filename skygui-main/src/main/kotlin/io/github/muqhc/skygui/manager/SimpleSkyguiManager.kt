package io.github.muqhc.skygui.manager

import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.event.SkyDisplayInteractEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

open class SimpleSkyguiManager: SkyguiManager {
    override val displays: MutableList<SkyDisplay> = mutableListOf()
    override val eventListener: Listener = object : Listener {
        @EventHandler
        fun onPlayerInteract(event: PlayerInteractEvent) {
            displays.forEach { display ->
                display.rayTrace(event.player.eyeLocation).let { traceResult ->
                    display.components.forEach { component ->
                        if (component.isPointIn(traceResult.hitLocationOnDisplay))
                            component.onClicked(SkyDisplayInteractEvent(
                                event,
                                display,
                                traceResult
                            ))
                    }
                }
            }
        }
    }
}