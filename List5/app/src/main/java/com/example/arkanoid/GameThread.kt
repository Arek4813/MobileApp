package com.example.arkanoid

import android.graphics.Canvas
import android.view.SurfaceHolder
import java.lang.Exception

class GameThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    private var running : Boolean = false
    private val FPS = 50
    private var canvas: Canvas? = null

    fun setRunning(isRunning : Boolean) {
        this.running=isRunning
    }

    override fun run() {

        var startTime: Long
        var timeMs : Long
        var waitTime : Long
        var targetTime = (1000/FPS).toLong()

        while(running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    gameView.update(40)
                    gameView.draw(canvas!!)
                }
            } catch (e : Exception) {
                e.printStackTrace()
            } finally {
                if(canvas!=null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }


            timeMs=(System.nanoTime()-startTime)/1000000
            waitTime=targetTime-timeMs

            try {
                sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }



        }
    }
}