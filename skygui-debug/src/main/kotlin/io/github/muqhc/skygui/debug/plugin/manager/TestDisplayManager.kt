package io.github.muqhc.skygui.debug.plugin.manager

import io.github.muqhc.skygui.debug.plugin.SkyguiDebugPlugin
import io.github.muqhc.skygui.manager.SimpleSkyguiManager

fun SkyguiDebugPlugin.testDisplayManager(): SimpleSkyguiManager =
    object: SimpleSkyguiManager() {

    }