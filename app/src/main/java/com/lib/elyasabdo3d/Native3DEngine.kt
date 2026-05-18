package com.lib.elyasabdo3d

object Native3DEngine {

    init {
        System.loadLibrary("elyasabdo3d")
    }

    external fun nativeInit()

    external fun nativeResize(width: Int, height: Int)

    external fun nativeBeginFrame(cameraYaw: Float, cameraPitch: Float)

    external fun nativeDrawModel(
        name: String,
        path: String,
        x: Float,
        y: Float,
        z: Float,
        scale: Float,
        animation: String
    )

    external fun nativeEndFrame()
}

object ModelLoaderNative {

    init {
        System.loadLibrary("elyasabdo3d")
    }

    external fun loadModel(name: String, path: String): Boolean

    external fun loadedCount(): Int
}