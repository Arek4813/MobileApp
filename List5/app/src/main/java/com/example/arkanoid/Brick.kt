package com.example.arkanoid

import android.graphics.RectF

class Brick (row: Int, column: Int, width: Int, height: Int) {

    var isVisible = true
    var padding = 10

    var brickRect = RectF((column*width+padding).toFloat(), (row*height+padding).toFloat(), (column*width+width-padding).toFloat(), (row*height+height-padding).toFloat())


}