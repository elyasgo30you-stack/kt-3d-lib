package com.lib.elyasabdo3d

data class Block3D(
    val name: String,
    val width: Float,
    val height: Float,
    val depth: Float,
    val x: Float,
    val y: Float,
    val z: Float,
    val colorName: String,
    val colorInt: Int
) {
    val volume: Float
        get() = Native3D.volume(width, height, depth)
}