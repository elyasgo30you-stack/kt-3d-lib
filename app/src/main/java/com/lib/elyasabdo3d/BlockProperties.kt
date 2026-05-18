package com.lib.elyasabdo3d

class BlockProperties {

    private var name: String = "Block"

    private var width: Float = 1f
    private var height: Float = 1f
    private var depth: Float = 1f

    private var x: Float = 0f
    private var y: Float = 0f
    private var z: Float = 0f

    private var colorName: String = "white"

    fun setName(value: String): BlockProperties {
        name = value
        return this
    }

    fun setSize(width: Float, height: Float, depth: Float): BlockProperties {
        this.width = width
        this.height = height
        this.depth = depth
        return this
    }

    fun setPosition(z: Float, y: Float, x: Float): BlockProperties {
        this.z = z
        this.y = y
        this.x = x
        return this
    }

    fun setColor(value: String): BlockProperties {
        colorName = value.lowercase()
        return this
    }

    fun toBlock(): Block3D {
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