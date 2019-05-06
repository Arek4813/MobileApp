package com.example.arkanoid

import android.graphics.RectF

class Bouncer (xSize: Int, ySize :Int){

    private var bouncerX = xSize.toFloat()/2
    private var bouncerY = ySize.toFloat()*9/10
    private var bouncerLength = 220f
    private var bouncerHeight = 40f
    private var paddleSpeed = 500
    val LEFT = -1
    val RIGHT = 1
    val STOPPED = 0
    var howIsMoving = STOPPED


    var rect = RectF(bouncerX, bouncerY, bouncerX+bouncerLength, bouncerY+bouncerHeight)

    fun update(fps : Long, width: Int) {
        if(howIsMoving==LEFT) {
            bouncerX=bouncerX-paddleSpeed/fps
        }
        if(howIsMoving==RIGHT) {
            bouncerX=bouncerX+paddleSpeed/fps
        }
        if(bouncerX<=0){
            bouncerX=0F
        }
        if(bouncerX+bouncerLength>=width) {
            bouncerX=width.toFloat()-bouncerLength
        }
        rect.left=bouncerX
        rect.right=bouncerX+bouncerLength

    }





}