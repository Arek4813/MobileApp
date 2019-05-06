package com.example.arkanoid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView(context: Context, attributeSet: AttributeSet) : SurfaceView(context, attributeSet), SurfaceHolder.Callback {

    private val thread: GameThread

    private val XSIZE = 1080//gameView.width
    private val YSIZE = 1420//gameView.height
    private val cols = 9
    private val rows = 4
    var score = 0
    var lives = 3

    var bouncer : Bouncer
    var ball : Ball
    var bricks = ArrayList<Brick>()

    val PREFS_FILENAME = "com.example.arkanoidtest"
    val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    private var ratio = prefs.getInt("highScore", 0)

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
        bouncer = Bouncer(XSIZE, YSIZE)
        ball = Ball(XSIZE, YSIZE)
        createBricks()
    }


    fun createBricks() {
        var brickOnX = 120
        var brickOnY = 150

        for(c in 0..cols-1) {
            for(r in 1..rows) {
                bricks.add(Brick(r, c, brickOnX, brickOnY))
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        thread.setRunning(false);
        thread.join();
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        thread.setRunning(true)
        thread.start()
    }

    fun update(fps: Long) {
        ball.update(fps)
        bouncer.update(fps, XSIZE)

        for(x in 0..cols*rows-1) {
            if(bricks[x].isVisible) {
                if(RectF.intersects(bricks[x].brickRect, ball.ballRect)) {
                    bricks[x].isVisible=false
                    ball.reverseY()
                    //ball.reverseX()
                    score = score + 10
                }
            }
        }


        if(RectF.intersects(bouncer.rect, ball.ballRect)) {
            ball.reverseY()
        }

        if(ball.ballRect.bottom>=YSIZE) {
            lives=lives-1
        }




    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if(canvas==null) return

        val bouncerColor = Paint()
        bouncerColor.setARGB(255, 40, 40, 255)
        val ballColor = Paint()
        ballColor.setARGB(255, 255, 30, 30)
        val brickColor = Paint()
        brickColor.setARGB(255, 130, 70, 40)
        val textColor = Paint()
        textColor.setARGB(255, 255, 255, 255)
        textColor.textSize = 50F
        val resulttextColor = Paint()
        resulttextColor.setARGB(255, 255, 255, 255)
        resulttextColor.textSize = 90F

        canvas.drawRect(bouncer.rect, bouncerColor)
        canvas.drawOval(ball.ballRect, ballColor)
        for(a in 0..cols*rows-1) {
            if(bricks[a].isVisible) {
                canvas.drawRect(bricks[a].brickRect, brickColor)
            }
        }
        canvas.drawText("Your score: "+ score, 15F, 90F, textColor)
        canvas.drawText("Lives left: "+ lives, XSIZE/3.toFloat()+20F, 90F, textColor)
        canvas.drawText("Your ratio: "+ratio, XSIZE*2/3.toFloat(), 90F, textColor)


        if(score==10*cols*rows) {
            ratio++
            with(prefs!!.edit()) {
                putInt("highScore", ratio)

                commit()
            }
            canvas.drawText("YOU WON A GAME!", 150F, YSIZE/2.toFloat(), resulttextColor)
            thread.setRunning(false)
        }

        if(lives<=0) {
            ratio--
            with(prefs!!.edit()) {
                putInt("highScore", ratio)

                commit()
            }
            canvas.drawText("YOU LOSE A GAME!", 150F, YSIZE/2.toFloat(), resulttextColor)
            thread.setRunning(false)
        }



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event!!.x>XSIZE/2) {
            bouncer.howIsMoving=1
        }
        else {
            bouncer.howIsMoving=-1
        }

        return super.onTouchEvent(event)
    }
}