package io.github.muqhc.skygui.util

import org.bukkit.Location

interface SkyRayTraceResult {
    val hitLocation: Location
    val hitLocationOnDisplay: Point
}