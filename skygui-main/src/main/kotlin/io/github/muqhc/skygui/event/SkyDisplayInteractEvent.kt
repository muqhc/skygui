package io.github.muqhc.skygui.event

import io.github.muqhc.skygui.SkyDisplay
import io.github.muqhc.skygui.util.Point
import io.github.muqhc.skygui.util.SkyRayTraceResult
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector

class SkyDisplayInteractEvent(
    minecraftEvent: PlayerInteractEvent,
    val display: SkyDisplay,
    val traceResult: SkyRayTraceResult
) : PlayerInteractEvent(
    minecraftEvent.player,
    minecraftEvent.action,
    minecraftEvent.item,
    minecraftEvent.clickedBlock,
    minecraftEvent.blockFace,
    minecraftEvent.hand,
    minecraftEvent.clickedPosition
) {
    val originEvent: PlayerInteractEvent = minecraftEvent
}