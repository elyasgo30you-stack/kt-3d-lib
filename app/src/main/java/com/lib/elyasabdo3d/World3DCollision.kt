package com.lib.elyasabdo3d

class World3DCollision(
    private val state: World3DState
) {

    private data class NearCallback(
        val a: String,
        val b: String,
        val radius: Float,
        val once: Boolean,
        val callback: () -> Unit,
        var fired: Boolean = false
    )

    private val callbacks = mutableListOf<NearCallback>()

    fun distance(a: String, b: String): Float {
        val posA = state.getPosition(a) ?: return Float.MAX_VALUE
        val posB = state.getPosition(b) ?: return Float.MAX_VALUE

        return World3DNativeMath.distance3D(
            ax = posA.x,
            ay = posA.y,
            az = posA.z,
            bx = posB.x,
            by = posB.y,
            bz = posB.z
        )
    }

    fun isNear(a: String, b: String, radius: Float): Boolean {
        if (radius < 0f) return false

        val posA = state.getPosition(a) ?: return false
        val posB = state.getPosition(b) ?: return false

        return World3DNativeMath.isNear3D(
            ax = posA.x,
            ay = posA.y,
            az = posA.z,
            bx = posB.x,
            by = posB.y,
            bz = posB.z,
            radius = radius
        )
    }

    fun onNear(
        a: String,
        b: String,
        radius: Float,
        once: Boolean = false,
        callback: () -> Unit
    ) {
        callbacks.add(
            NearCallback(
                a = a,
                b = b,
                radius = radius,
                once = once,
                callback = callback
            )
        )
    }

    fun checkAll() {
        for (item in callbacks) {
            if (item.once && item.fired) {
                continue
            }

            if (isNear(item.a, item.b, item.radius)) {
                item.callback.invoke()

                if (item.once) {
                    item.fired = true
                }
            }
        }
    }

    fun clearCallbacks() {
        callbacks.clear()
    }
}
