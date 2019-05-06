package com.example.arkanoid

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Ball(xSize: Int, ySize :Int) {

    var ballSize = 45f
    private var dx = 7f
    private var dy = 10f
    private var ballX = xSize.toFloat()/2
    private var ballY = ySize.toFloat()*2/3
    var ballRect = RectF(xSize.toFloat()/2, ySize.toFloat()/2, (xSize.toFloat())/2+ballSize, (ySize.toFloat()/2)+ballSize)
    var tmpxSize = xSize
    var tmpySize = ySize

    fun update (fps: Long) {

        ballX+=dx
        ballY+=dy

        if (ballX <= 0 || ballX+ballSize >= tmpxSize) {
            dx = -dx
        }
        if (ballY <= 0 || ballY+ballSize >= tmpySize) {
            dy = -dy
        }

        ballRect.left=ballX
        ballRect.top=ballY
        ballRect.right=ballX+ballSize
        ballRect.bottom=ballY+ballSize


    }

    fun reverseY () {
        dy=-dy
    }

    fun reverseX () {
        dx=-dx
    }

}