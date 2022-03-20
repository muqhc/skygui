package io.github.muqhc.skygui.util

import kotlin.math.sqrt

data class Vector2D(var x: Double, var y: Double) {
    constructor (xy: Pair<Double,Double>) : this(xy.first,xy.second)
    constructor (point: Point) : this(point.x,point.y)

    fun splitBetweenVectorWith(other: Vector2D, times: Int) : List<Vector2D> = sequence {
        for (i in 0..(times+1))
            yield(
                (other - this@Vector2D) / (times + 1) * i + this@Vector2D
            )
    }.toList()

    fun length() = sqrt((x*x)+(y*y))
    infix fun distance(other: Vector2D) = (this - other).length()

    //region Vector2D Calculation with Scalar

    operator fun plus       (value: Double) = Vector2D(x+value,y+value)
    operator fun minus      (value: Double) = Vector2D(x-value,y-value)
    operator fun times      (value: Double) = Vector2D(x*value,y*value)
    operator fun div        (value: Double) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Double) {x += value ; y += value}
    operator fun minusAssign(value: Double) {x -= value ; y -= value}
    operator fun timesAssign(value: Double) {x *= value ; y *= value}
    operator fun divAssign  (value: Double) {x /= value ; y /= value}

    operator fun plus       (value: Int) = Vector2D(x+value,y+value)
    operator fun minus      (value: Int) = Vector2D(x-value,y-value)
    operator fun times      (value: Int) = Vector2D(x*value,y*value)
    operator fun div        (value: Int) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Int) {x += value ; y += value}
    operator fun minusAssign(value: Int) {x -= value ; y -= value}
    operator fun timesAssign(value: Int) {x *= value ; y *= value}
    operator fun divAssign  (value: Int) {x /= value ; y /= value}

    operator fun plus       (value: Float) = Vector2D(x+value,y+value)
    operator fun minus      (value: Float) = Vector2D(x-value,y-value)
    operator fun times      (value: Float) = Vector2D(x*value,y*value)
    operator fun div        (value: Float) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Float) {x += value ; y += value}
    operator fun minusAssign(value: Float) {x -= value ; y -= value}
    operator fun timesAssign(value: Float) {x *= value ; y *= value}
    operator fun divAssign  (value: Float) {x /= value ; y /= value}

    operator fun plus       (value: Long) = Vector2D(x+value,y+value)
    operator fun minus      (value: Long) = Vector2D(x-value,y-value)
    operator fun times      (value: Long) = Vector2D(x*value,y*value)
    operator fun div        (value: Long) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Long) {x += value ; y += value}
    operator fun minusAssign(value: Long) {x -= value ; y -= value}
    operator fun timesAssign(value: Long) {x *= value ; y *= value}
    operator fun divAssign  (value: Long) {x /= value ; y /= value}

    operator fun plus       (value: Short) = Vector2D(x+value,y+value)
    operator fun minus      (value: Short) = Vector2D(x-value,y-value)
    operator fun times      (value: Short) = Vector2D(x*value,y*value)
    operator fun div        (value: Short) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Short) {x += value ; y += value}
    operator fun minusAssign(value: Short) {x -= value ; y -= value}
    operator fun timesAssign(value: Short) {x *= value ; y *= value}
    operator fun divAssign  (value: Short) {x /= value ; y /= value}

    operator fun plus       (value: Byte) = Vector2D(x+value,y+value)
    operator fun minus      (value: Byte) = Vector2D(x-value,y-value)
    operator fun times      (value: Byte) = Vector2D(x*value,y*value)
    operator fun div        (value: Byte) = Vector2D(x/value,y/value)
    operator fun plusAssign (value: Byte) {x += value ; y += value}
    operator fun minusAssign(value: Byte) {x -= value ; y -= value}
    operator fun timesAssign(value: Byte) {x *= value ; y *= value}
    operator fun divAssign  (value: Byte) {x /= value ; y /= value}

    //endregion

    operator fun plus       (value: Vector2D) = Vector2D(x+value.x,y+value.y)
    operator fun minus      (value: Vector2D) = Vector2D(x-value.x,y-value.y)
    operator fun times      (value: Vector2D) = Vector2D(x*value.x,y*value.y)
    operator fun div        (value: Vector2D) = Vector2D(x/value.x,y/value.y)
    operator fun plusAssign (value: Vector2D) {x += value.x ; y += value.y}
    operator fun minusAssign(value: Vector2D) {x -= value.x ; y -= value.y}
    operator fun timesAssign(value: Vector2D) {x *= value.x ; y *= value.y}
    operator fun divAssign  (value: Vector2D) {x /= value.x ; y /= value.y}

    operator fun unaryMinus() = Vector2D(-x,-y)

    fun toPoint() = Point(x,y)
}
