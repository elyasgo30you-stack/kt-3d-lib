package com.lib.elyasabdo3d

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class Engine3DView(context: Context) : GLSurfaceView(context) {

    private val renderer3D = NativeRenderer(context)
    private var player: Model3D? = null

    private var lastX = 0f
    private var lastY = 0f

    private var joystickStartX = 0f
    private var joystickStartY = 0f
    private var joystickActive = false

    init {
        setEGLContextClientVersion(2)
        setRenderer(renderer3D)
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    fun addModel(model: Model3D) {
        renderer3D.addModel(model)
    }

    fun setPlayer(model: Model3D) {
        player = model
        renderer3D.player = model
    }

    fun addKeyFrame(keyFrame: KeyFrame3D) {
        renderer3D.addKeyFrame(keyFrame)
    }

    fun setJumpPoint(modelName: String, z: Float, x: Float, y: Float) {
        renderer3D.setJumpPoint(modelName, z, x, y)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y

                if (event.x < width / 2f) {
                    joystickActive = true
                    joystickStartX = event.x
                    joystickStartY = event.y
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (joystickActive && event.x < width / 2f) {
                    val dx = event.x - joystickStartX
                    val dy = event.y - joystickStartY
                    val speed = PlayerLogic.getSpeed(context)

                    player?.let {
                        it.x += dx / 100f * speed * 0.01f
                        it.z += dy / 100f * speed * 0.01f
                        it.currentAnimation = "walk"
                    }
                } else {
                    val dx = event.x - lastX
                    val dy = event.y - lastY

                    renderer3D.cameraYaw += dx * 0.4f
                    renderer3D.cameraPitch += dy * 0.4f

                    lastX = event.x
                    lastY = event.y
                }
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                joystickActive = false
                player?.currentAnimation = "idle"
            }
        }

        return true
    }
}

data class Model3D(
    val name: String,
    val path: String,
    var x: Float,
    var y: Float,
    var z: Float,
    var scale: Float,
    var currentAnimation: String = "idle"
)

data class KeyFrame3D(
    val modelName: String,
    val fromSec: Float,
    val toSec: Float,
    val action: String,
    val file: String? = null
)

class NativeRenderer(
    private val context: Context
) : Renderer {

    private val models = mutableListOf<Model3D>()
    private val keyFrames = mutableListOf<KeyFrame3D>()
    private val jumpPoints = mutableMapOf<String, Triple<Float, Float, Float>>()

    var player: Model3D? = null
    var cameraYaw = 0f
    var cameraPitch = 0f

    override fun onSurfaceCreated(
        gl: javax.microedition.khronos.opengles.GL10?,
        config: javax.microedition.khronos.egl.EGLConfig?
    ) {
        Native3DEngine.nativeInit()
    }

    override fun onSurfaceChanged(
        gl: javax.microedition.khronos.opengles.GL10?,
        width: Int,
        height: Int
    ) {
        Native3DEngine.nativeResize(width, height)
    }

    override fun onDrawFrame(gl: javax.microedition.khronos.opengles.GL10?) {
        player?.let {
            PlayerLogic.update(context, it)
        }

        Native3DEngine.nativeBeginFrame(cameraYaw, cameraPitch)

        for (model in models) {
            Native3DEngine.nativeDrawModel(
                model.name,
                model.path,
                model.x,
                model.y,
                model.z,
                model.scale,
                model.currentAnimation
            )
        }

        Native3DEngine.nativeEndFrame()
    }

    fun addModel(model: Model3D) {
        models.add(model)
        ModelLoaderNative.loadModel(model.name, model.path)
    }

    fun addKeyFrame(frame: KeyFrame3D) {
        keyFrames.add(frame)
    }

    fun setJumpPoint(modelName: String, z: Float, x: Float, y: Float) {
        jumpPoints[modelName] = Triple(z, x, y)
    }
}