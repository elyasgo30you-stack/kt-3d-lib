package com.lib.elyasabdo3d

class World3DCollision(
    private val state: World3DState
) {

    private data class NearCallback(
        val a: String,
        val b: String,
        val radius: Float,
        val callback: () -> Unit
    )

    private val callbacks = mutableListOf<NearCallback>()

    fun distance(a: String, b: String): Float {
        val posA = state.getPosition(a) ?: return Float.MAX_VALUE
        val posB = state.getPosition(b) ?: return Float.MAX_VALUE

        return World3DNativeMath.distance3D(
            posA.x,
            posA.y,
            posA.z,
            posB.x,
            posB.y,
            posB.z
        )
    }

    fun isNear(a: String, b: String, radius: Float): Boolean {
        val posA = state.getPosition(a) ?: return false
        val posB = state.getPosition(b) ?: return false

        return World3DNativeMath.isNear3D(
            posA.x,
            posA.y,
            posA.z,
            posB.x,
            posB.y,
            posB.z,
            radius
        )
    }

    fun onNear(a: String, b: String, radius: Float, callback: () -> Unit) {
        callbacks.add(
            NearCallback(
                a = a,
                b = b,
                radius = radius,
                callback = callback
            )
        )
    }

    fun checkAll() {
        for (item in callbacks) {
            if (isNear(item.a, item.b, item.radius)) {
                item.callback.invoke()
            }
        }
    }

    fun clearCallbacks() {
        callbacks.clear()
    }
}

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