package com.example.kkoikrzyyk

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Math.abs
import java.lang.Math.random
import java.util.*

class MainActivity : AppCompatActivity() {

    var P1wins = 0
    var P2wins = 0
    var botFlag=false
    var bugFlag=false
    var currPlayer = "P1"
    var botMovesCounter = 1
    var P1 = ArrayList<Int>()
    var P2 = ArrayList<Int>()
    var buttons = ArrayList<Button>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        P1TV.text="P1 wins: $P1wins"
        P2TV.text="P2 wins: $P2wins"
        queueTV.text="$currPlayer has a move"
        loopThrough(findViewById<ViewGroup>(R.id.playLayout))
        ResetButton.setOnClickListener {
            newGameOperations()
        }
    }

    fun newGameOperations () {
        bugFlag=false
        P1.clear()
        P2.clear()
        currPlayer="P1"
        queueTV.text="$currPlayer has a move"
        for(button in buttons) {
            button.text=""
            button.isClickable=true
        }

        if(playWithComputer.isChecked) {
            if(botFlag==false) {
                P1wins=0
                P2wins=0
            }
            P1TV.text="P1 wins: $P1wins"
            P2TV.text="Bot wins: $P2wins"
            botFlag=true
        }
        else {
            if (botFlag == true) {
                P1wins = 0
                P2wins = 0
                P1TV.text = "P1 wins: $P1wins"
                P2TV.text = "P2 wins: $P2wins"
                botFlag = false
            }
        }
    }

    private fun botPlay() {
        var emptyCells = ArrayList<Int>()
        for(buttonID in 1..25) {
            if(!(P1.contains(buttonID) || (P2.contains(buttonID)))) {
                emptyCells.add(buttonID)
            }
        }

        //randomly

        val r = Random()
        var randInd = r.nextInt(emptyCells.size)


        //better than random strategy - badly verified

        /*var closestCells = ArrayList<Int>()
        for(buttonId in 1..25) {
            if((buttonId % 5 == prevClicked % 5) || (buttonId==prevClicked+1) || (buttonId==prevClicked-1) || (buttonId==prevClicked+5) || (buttonId==prevClicked-5)) {
                closestCells.add(buttonId)
            }
        }
        var randInd = 0
        do {
            randInd=r.nextInt(closestCells.size)
        }
        while (emptyCells.contains(randInd))*/

        /*do {
            when (botMovesCounter) {
                1 -> randInd = 13
                2 -> randInd = r.nextInt(5) + 6
                3 -> randInd = r.nextInt(5) + 16
                4 -> randInd = r.nextInt(5) + 1
                5 -> randInd = r.nextInt(5) + 11
                else -> randInd = r.nextInt(emptyCells.size)
            }
        }
        while(emptyCells.contains(randInd))
        botMovesCounter++*/

        var cell = emptyCells[randInd]
        var selButton:Button
        when (cell) {
            1 -> selButton = Button00
            2 -> selButton = Button01
            3 -> selButton = Button02
            4 -> selButton = Button03
            5 -> selButton = Button04
            6 -> selButton = Button10
            7 -> selButton = Button11
            8 -> selButton = Button12
            9 -> selButton = Button13
            10 -> selButton = Button14
            11 -> selButton = Button20
            12 -> selButton = Button21
            13 -> selButton = Button22
            14 -> selButton = Button23
            15 -> selButton = Button24
            16 -> selButton = Button30
            17 -> selButton = Button31
            18 -> selButton = Button32
            19 -> selButton = Button33
            20 -> selButton = Button34
            21 -> selButton = Button40
            22 -> selButton = Button41
            23 -> selButton = Button42
            24 -> selButton = Button43
            25 -> selButton = Button44
            else -> selButton = Button00
        }

        playing(cell, selButton)

    }

    private fun loopThrough(parent: ViewGroup) {
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            if (child is Button) buttons.add(child)
            else if (child is ViewGroup) loopThrough(child)
        }
    }

    fun clicker(view : View) {
        val selButton = view as Button
        var buttonID = 0
        when (selButton.id) {
            R.id.Button00 -> buttonID = 1
            R.id.Button01 -> buttonID = 2
            R.id.Button02 -> buttonID = 3
            R.id.Button03 -> buttonID = 4
            R.id.Button04 -> buttonID = 5
            R.id.Button10 -> buttonID = 6
            R.id.Button11 -> buttonID = 7
            R.id.Button12 -> buttonID = 8
            R.id.Button13 -> buttonID = 9
            R.id.Button14 -> buttonID = 10
            R.id.Button20 -> buttonID = 11
            R.id.Button21 -> buttonID = 12
            R.id.Button22 -> buttonID = 13
            R.id.Button23 -> buttonID = 14
            R.id.Button24 -> buttonID = 15
            R.id.Button30 -> buttonID = 16
            R.id.Button31 -> buttonID = 17
            R.id.Button32 -> buttonID = 18
            R.id.Button33 -> buttonID = 19
            R.id.Button34 -> buttonID = 20
            R.id.Button40 -> buttonID = 21
            R.id.Button41 -> buttonID = 22
            R.id.Button42 -> buttonID = 23
            R.id.Button43 -> buttonID = 24
            R.id.Button44 -> buttonID = 25
        }

        playing(buttonID, selButton)
    }


    private fun playing(buttonID: Int, selButton: Button) {

        if(currPlayer=="P1") {
            selButton.text="X"
            selButton.setTextColor(Color.parseColor("#fc7b86"))
            P1.add(buttonID)
            currPlayer="P2"
            if(botFlag==true) {
                if(P1.size<13) botPlay()
                else {
                    for(button in buttons) {
                        button.isClickable=false
                    }
                    Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            selButton.text="O"
            selButton.setTextColor(Color.parseColor("#43bcaa"))
            P2.add(buttonID)
            currPlayer="P1"
        }
        selButton.isClickable=false
        queueTV.text="$currPlayer has a move"
        if(bugFlag==false) { checkWinner() }
        if(P1.size==13 || P2.size==13)  {
            for(button in buttons) {
                button.isClickable=false
            }
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkWinner() {
        var isWinner = -10

        //p1 winning
        if(P1.contains(1) && P1.contains(6) && P1.contains(11) && P1.contains(16) && P1.contains(21)) {
            isWinner=1
        }
        if(P1.contains(2) && P1.contains(7) && P1.contains(12) && P1.contains(17) && P1.contains(22)) {
            isWinner=1
        }
        if(P1.contains(3) && P1.contains(8) && P1.contains(13) && P1.contains(18) && P1.contains(23)) {
            isWinner=1
        }
        if(P1.contains(4) && P1.contains(9) && P1.contains(14) && P1.contains(19) && P1.contains(24)) {
            isWinner=1
        }
        if(P1.contains(5) && P1.contains(10) && P1.contains(15) && P1.contains(20) && P1.contains(25)) {
            isWinner=1
        }
        if(P1.contains(1) && P1.contains(2) && P1.contains(3) && P1.contains(4) && P1.contains(5)) {
            isWinner=1
        }
        if(P1.contains(6) && P1.contains(7) && P1.contains(8) && P1.contains(9) && P1.contains(10)) {
            isWinner=1
        }
        if(P1.contains(11) && P1.contains(12) && P1.contains(13) && P1.contains(14) && P1.contains(15)) {
            isWinner=1
        }
        if(P1.contains(16) && P1.contains(17) && P1.contains(18) && P1.contains(19) && P1.contains(20)) {
            isWinner=1
        }
        if(P1.contains(21) && P1.contains(22) && P1.contains(23) && P1.contains(24) && P1.contains(25)) {
            isWinner=1
        }
        if(P1.contains(1) && P1.contains(7) && P1.contains(13) && P1.contains(19) && P1.contains(25)) {
            isWinner=1
        }
        if(P1.contains(5) && P1.contains(9) && P1.contains(13) && P1.contains(17) && P1.contains(21)) {
            isWinner=1
        }


        //p2 winning
        if(P2.contains(1) && P2.contains(6) && P2.contains(11) && P2.contains(16) && P2.contains(21)) {
            isWinner=2
        }
        if(P2.contains(2) && P2.contains(7) && P2.contains(12) && P2.contains(17) && P2.contains(22)) {
            isWinner=2
        }
        if(P2.contains(3) && P2.contains(8) && P2.contains(13) && P2.contains(18) && P2.contains(23)) {
            isWinner=2
        }
        if(P2.contains(4) && P2.contains(9) && P2.contains(14) && P2.contains(19) && P2.contains(24)) {
            isWinner=2
        }
        if(P2.contains(5) && P2.contains(10) && P2.contains(15) && P2.contains(20) && P2.contains(25)) {
            isWinner=2
        }
        if(P2.contains(1) && P2.contains(2) && P2.contains(3) && P2.contains(4) && P2.contains(5)) {
            isWinner=2
        }
        if(P2.contains(6) && P2.contains(7) && P2.contains(8) && P2.contains(9) && P2.contains(10)) {
            isWinner=2
        }
        if(P2.contains(11) && P2.contains(12) && P2.contains(13) && P2.contains(14) && P2.contains(15)) {
            isWinner=2
        }
        if(P2.contains(16) && P2.contains(17) && P2.contains(18) && P2.contains(19) && P2.contains(20)) {
            isWinner=2
        }
        if(P2.contains(21) && P2.contains(22) && P2.contains(23) && P2.contains(24) && P2.contains(25)) {
            isWinner=2
        }
        if(P2.contains(1) && P2.contains(7) && P2.contains(13) && P2.contains(19) && P2.contains(25)) {
            isWinner=2
        }
        if(P2.contains(5) && P2.contains(9) && P2.contains(13) && P2.contains(17) && P2.contains(21)) {
            isWinner=2
        }



        //having winner
        if (isWinner==1) {
            Toast.makeText(this, "Player 1 won the game!", Toast.LENGTH_SHORT).show()
            bugFlag=true
            P1wins++
            P1TV.text="P1 wins: $P1wins"
            for(button in buttons) {
                button.isClickable=false
            }
        }
        if (isWinner==2) {
            Toast.makeText(this, "Player 2 won the game!", Toast.LENGTH_SHORT).show()
            bugFlag=true
            P2wins++
            if(botFlag==true) P2TV.text="Bot wins: $P2wins"
            else P2TV.text="P2 wins: $P2wins"
            for(button in buttons) {
                button.isClickable=false
            }
        }

    }
}

