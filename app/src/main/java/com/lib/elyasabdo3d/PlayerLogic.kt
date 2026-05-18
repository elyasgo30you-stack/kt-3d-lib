package com.lib.elyasabdo3d

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object PlayerLogic {

    private var velocityY = 0f
    private var isJumping = false

    fun getSpeed(context: Context): Float {
        return readFloatFromAssets(
            context = context,
            path = "characters/setings/speed.txt",
            defaultValue = 20f
        )
    }

    fun getJumpPower(context: Context, modelName: String? = null): Float {
        val path = if (modelName != null) {
            "charecters/seting/jump_$modelName.txt"
        } else {
            "charecters/seting/jump.txt"
        }

        return readFloatFromAssets(
            context = context,
            path = path,
            defaultValue = 200f
        )
    }

    fun jump(model: Model3D) {
        if (!isJumping) {
            velocityY = 200f
            isJumping = true
            model.currentAnimation = "jump"
        }
    }

    fun update(context: Context, model: Model3D) {
        if (isJumping) {
            model.y += velocityY * 0.016f
            velocityY -= 480f * 0.016f

            if (model.y <= 0f) {
                model.y = 0f
                velocityY = 0f
                isJumping = false
                model.currentAnimation = "idle"
            }
        }
    }

    private fun readFloatFromAssets(
        context: Context,
        path: String,
        defaultValue: Float
    ): Float {
        return try {
            val input = context.assets.open(path)
            val reader = BufferedReader(InputStreamReader(input))
            val value = reader.readLine()?.trim()?.toFloatOrNull()
            reader.close()
            value ?: defaultValue
        } catch (_: Exception) {
            defaultValue
        }
    }
}