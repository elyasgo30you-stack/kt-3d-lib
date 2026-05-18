package com.lib.elyasabdo3d

import android.app.Activity
import android.widget.Button
import android.widget.FrameLayout

object world3d {

    val state: World3DState = World3DState()
    val collision: World3DCollision = World3DCollision(state)

    private var engine: Engine3DView? = null
    private var player: Model3D? = null

    fun start(activity: Activity): Engine3DView {
        val view = Engine3DView(activity)
        engine = view

        val root = FrameLayout(activity)
        root.addView(view)

        val jumpButton = Button(activity)
        jumpButton.text = "Jump"

        val params = FrameLayout.LayoutParams(220, 120)
        params.leftMargin = 30
        params.topMargin = 900
        root.addView(jumpButton, params)

        jumpButton.setOnClickListener {
            player?.let { PlayerLogic.jump(it) }
        }

        activity.setContentView(root)

        val defaultPlayer = Model3D(
            name = "player",
            path = "characters/player.glb",
            x = 0f,
            y = 0f,
            z = 0f,
            scale = 1f
        )

        player = defaultPlayer
        view.addModel(defaultPlayer)
        view.setPlayer(defaultPlayer)

        state.setPosition(
            name = "player",
            x = defaultPlayer.x,
            y = defaultPlayer.y,
            z = defaultPlayer.z
        )

        return view
    }

    fun player(path: String = "characters/player.glb"): Model3D {
        val model = Model3D(
            name = "player",
            path = path,
            x = 0f,
            y = 0f,
            z = 0f,
            scale = 1f
        )

        player = model
        engine?.addModel(model)
        engine?.setPlayer(model)

        state.setPosition(
            name = "player",
            x = model.x,
            y = model.y,
            z = model.z
        )

        return model
    }

    object new {

        fun model(
            name: String,
            path: String,
            x: Float = 0f,
            y: Float = 0f,
            z: Float = 0f,
            scale: Float = 1f
        ): Model3D {
            val model = Model3D(
                name = name,
                path = path,
                x = x,
                y = y,
                z = z,
                scale = scale
            )

            engine?.addModel(model)

            state.setPosition(
                name = name,
                x = x,
                y = y,
                z = z
            )

            return model
        }

        fun keyframe(
            modelName: String,
            fromSec: Float,
            toSec: Float,
            action: String,
            file: String? = null
        ): KeyFrame3D {
            val frame = KeyFrame3D(
                modelName = modelName,
                fromSec = fromSec,
                toSec = toSec,
                action = action,
                file = file
            )

            engine?.addKeyFrame(frame)

            return frame
        }
    }

    object jump {
        fun button() {
            player?.let { PlayerLogic.jump(it) }
        }
    }

    object set {
        fun jump(modelName: String, z: Float, x: Float, y: Float) {
            engine?.setJumpPoint(modelName, z, x, y)
        }
    }
}
