package com.lib.elyasabdo3d

class BlockBuilder {

    private var width: Float = 1f
    private var height: Float = 1f
    private var depth: Float = 1f

    private var x: Float = 0f
    private var y: Float = 0f
    private var z: Float = 0f

    private var colorName: String = "white"

    fun size(width: Float, height: Float, depth: Float): BlockBuilder {
        this.width = width
        this.height = height
        this.depth = depth
        return this
    }

    fun position(z: Float, y: Float, x: Float): BlockBuilder {
        this.z = z
        this.y = y
        this.x = x
        return this
    }

    fun color(color: String): BlockBuilder {
        this.colorName = color.lowercase()
        return this
    }

    internal fun build(name: String): Block3D {
        return Block3D(
            name = name,
            width = width,
            height = height,
            depth = depth,
            x = x,
            y = y,
            z = z,
            colorName = colorName,
            colorInt = Native3D.color(colorName)
        )
    }
}