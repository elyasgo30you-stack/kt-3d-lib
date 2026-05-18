package com.lib.elyasabdo3d

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Block3DView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var block: Block3D = abdo3d.block("Block") {
        size(1f, 1f, 1f)
        position(z = 0f, y = 0f, x = 0f)
        color("white")
    }

    init {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Block3DView)

            val name = typedArray.getString(R.styleable.Block3DView_blockName) ?: "Block"

            val width = typedArray.getFloat(R.styleable.Block3DView_blockWidth3D, 1f)
            val height = typedArray.getFloat(R.styleable.Block3DView_blockHeight3D, 1f)
            val depth = typedArray.getFloat(R.styleable.Block3DView_blockDepth3D, 1f)

            val x = typedArray.getFloat(R.styleable.Block3DView_blockX3D, 0f)
            val y = typedArray.getFloat(R.styleable.Block3DView_blockY3D, 0f)
            val z = typedArray.getFloat(R.styleable.Block3DView_blockZ3D, 0f)

            val color = typedArray.getString(R.styleable.Block3DView_blockColorName) ?: "white"

            block = abdo3d.block(name) {
                size(width, height, depth)
                position(z = z, y = y, x = x)
                color(color)
            }

            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = block.colorInt

        val left = 30f + block.x
        val top = 30f + block.y
        val right = left + block.width * 50f
        val bottom = top + block.height * 50f

        canvas.drawRect(left, top, right, bottom, paint)

        paint.color = 0xFF000000.toInt()
        paint.textSize = 28f
        canvas.drawText(block.name, left, bottom + 35f, paint)

        paint.textSize = 20f
        canvas.drawText("volume: ${block.volume}", left, bottom + 65f, paint)
    }

    fun setBlock(block: Block3D) {
        this.block = block
        invalidate()
    }

    fun getBlock(): Block3D {
        return block
    }

    fun setBlockName(name: String) {
        block = block.copy(name = name)
        invalidate()
    }

    fun setBlockSize(width: Float, height: Float, depth: Float) {
        block = block.copy(
            width = width,
            height = height,
            depth = depth
        )
        invalidate()
    }

    fun setBlockPosition(z: Float, y: Float, x: Float) {
        block = block.copy(
            z = z,
            y = y,
            x = x
        )
        invalidate()
    }

    fun setBlockColor(color: String) {
        block = block.copy(
            colorName = color.lowercase(),
            colorInt = Native3D.color(color)
        )
        invalidate()
    }
}