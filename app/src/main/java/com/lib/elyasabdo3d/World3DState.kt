package com.lib.elyasabdo3d

class World3DState {

    data class Vec3(
        val x: Float,
        val y: Float,
        val z: Float
    )

    private val positions = mutableMapOf<String, Vec3>()

    fun setPosition(name: String, x: Float, y: Float, z: Float) {
        positions[name] = Vec3(x = x, y = y, z = z)
    }

    fun getPosition(name: String): Vec3? {
        return positions[name]
    }

    fun getPositionOrZero(name: String): Vec3 {
        return positions[name] ?: Vec3(0f, 0f, 0f)
    }

    fun moveBy(name: String, dx: Float, dy: Float, dz: Float) {
        val current = getPositionOrZero(name)

        positions[name] = Vec3(
            x = current.x + dx,
            y = current.y + dy,
            z = current.z + dz
        )
    }

    fun has(name: String): Boolean {
        return positions.containsKey(name)
    }

    fun remove(name: String) {
        positions.remove(name)
    }

    fun clear() {
        positions.clear()
    }
}
