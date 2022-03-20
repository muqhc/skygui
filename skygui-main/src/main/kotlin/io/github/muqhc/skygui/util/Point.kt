package io.github.muqhc.skygui.util

import java.io.Serializable

data class Point(val x: Double, val y: Double): Serializable {
    constructor(x: Int, y: Int): this(x.toDouble(),y.toDouble())
    override fun toString(): String = "${this::class.simpleName}{x=$x,y=$y}"
}