package com.lib.elyasabdo3d

object Native3D {

    init {
        System.loadLibrary("elyasabdo3d")
    }

    external fun getVersion(): String

    external fun volume(width: Float, height: Float, depth: Float): Float

    external fun color(name: String): Int
}