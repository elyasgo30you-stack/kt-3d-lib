package com.lib.elyasabdo3d

object World3DNativeMath {

    init {
        System.loadLibrary("elyasabdo3d")
    }

    external fun distance3D(
        ax: Float,
        ay: Float,
        az: Float,
        bx: Float,
        by: Float,
        bz: Float
    ): Float

    external fun isNear3D(
        ax: Float,
        ay: Float,
        az: Float,
        bx: Float,
        by: Float,
        bz: Float,
        radius: Float
    ): Boolean
}
