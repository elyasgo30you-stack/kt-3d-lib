package com.lib.elyasabdo3d

object abdo3d {

    fun block(name: String, setup: BlockBuilder.() -> Unit): Block3D {
        val builder = BlockBuilder()
        builder.setup()
        return builder.build(name)
    }

    fun properties(): BlockProperties {
        return BlockProperties()
    }

    fun world(): World3D {
        return World3D()
    }

    fun nativeVersion(): String {
        return Native3D.getVersion()
    }

    fun nativeVolume(width: Float, height: Float, depth: Float): Float {
        return Native3D.volume(width, height, depth)
    }
}