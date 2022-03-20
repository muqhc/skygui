package io.github.muqhc.skygui.debug.plugin

import io.github.monun.kommand.kommand
import io.github.muqhc.skygui.component.SkyComponent
import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.debug.plugin.component.MeowPiano
import io.github.muqhc.skygui.manager.SimpleSkyguiManager
import io.github.muqhc.skygui.util.Point
import org.bukkit.Location
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import org.bukkit.util.Vector

class SkyguiDebugPlugin: JavaPlugin(), Listener {
    val testDisplayManager = SimpleSkyguiManager()
    var playTask: BukkitTask? = null
    override fun onEnable() {
        kommand {
            register("skygui") {
                then("clear") {
                    executes {
                        testDisplayManager.displays.clear()
                    }
                }
                then("put-display") {
                    requires { isPlayer }
                    executes {
                        testDisplayManager.displays += object : SkyDisplay {
                            override val components: MutableList<SkyComponent> = mutableListOf(MeowPiano(Point(0,0)))
                            override val normalVector: Vector = player.location.direction.clone().multiply(-1).normalize()
                            override val location: Location = player.location.clone()
                        }
                    }
                }
            }
        }
        playTask = testDisplayManager.manageStart(this)
    }

    override fun onDisable() {
        playTask?.cancel()
        playTask = null
    }
}