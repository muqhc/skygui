package io.github.muqhc.skygui.manager

import io.github.muqhc.skygui.SkyDisplay
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

interface SkyguiManager {
    val eventListener: Listener
    val displays: MutableList<SkyDisplay>
    fun managing() {
        displays.forEach { it.render() }
    }
    fun manageStart(plugin: Plugin) =
        object: BukkitRunnable() {
            override fun run() = managing()
        }.runTaskTimer(plugin,0L,1L).also {
            plugin.server.pluginManager.registerEvents(eventListener,plugin)
        }
}