package com.example.wisielec

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var duplicateWord = ""
    var attempts = 9
    var pathCounter = 0
    var flag=false
    var checkDecider=true
    val rand = Random()
    var wordList = arrayOf("logika")
    var arrayLength = 0
    var rdm = 0
    var orgWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordList = resources.getStringArray(R.array.words)
        arrayLength=wordList.size
        rdm = rand.nextInt(arrayLength)
        orgWord=wordList[rdm].toUpperCase()
        converter()
        howManyAttTV.text="Left attempts: $attempts"
        checkButton.setOnClickListener {
            if(checkDecider==true) {
                buttonActions()
            }
            else {
                wordTV.text=orgWord
            }
        }
        newGameButton.setOnClickListener {
            rdm = rand.nextInt(arrayLength)
            orgWord=wordList[rdm].toUpperCase()
            attempts=9
            pathCounter=0
            checkButton.isClickable=true
            letterPT.text.clear()
            checkDecider=true
            checkButton.text = "CHECK"
            howManyAttTV.text="Left attempts: $attempts"
            duplicateWord=""
            converter()
            communicatorTV.text=""
            pictureIV.setImageResource(R.drawable.s0)
        }
    }

    fun buttonActions() {
        flag=false
        letterChecker(letterPT.text.toString())
        wordTV.text=duplicateWord
        letterPT.text.clear()
        hideKeyboard()
    }

    fun converter() {

        for(i in 1..orgWord.length) {
            duplicateWord=duplicateWord+"-"
        }
        wordTV.text = duplicateWord
    }

    fun letterChecker(tmpLetter: String) {
        for(i in 0..orgWord.length-1) {
            if (orgWord.get(i).toString().equals(tmpLetter)) {
                duplicateWord = change(i, tmpLetter, duplicateWord)
                flag=true
                if (orgWord.equals(duplicateWord)) {
                    communicatorTV.text = "YOU WON!"
                    pictureIV.setImageResource(R.drawable.win)
                    checkButton.isClickable=false
                }
            }
        }
        if(flag==false) {
            if (attempts > 0) {
                attempts--
                pathCounter++
                howManyAttTV.text = "Left attempts: $attempts"
                when(pathCounter) {
                    1 -> pictureIV.setImageResource(R.drawable.s1)
                    2 -> pictureIV.setImageResource(R.drawable.s2)
                    3 -> pictureIV.setImageResource(R.drawable.s3)
                    4 -> pictureIV.setImageResource(R.drawable.s4)
                    5 -> pictureIV.setImageResource(R.drawable.s5)
                    6 -> pictureIV.setImageResource(R.drawable.s6)
                    7 -> pictureIV.setImageResource(R.drawable.s7)
                    8 -> pictureIV.setImageResource(R.drawable.s8)
                    9 -> pictureIV.setImageResource(R.drawable.s9)
                }
            }
            if (attempts==0) {
                communicatorTV.text = "YOU LOSE!"
                //checkButton.isClickable=false
                checkButton.text = "SHOW"
                checkDecider=false
            }
        }
    }

    fun change(place: Int, letter: String, word:String): String {
        return word.substring(0, place)+letter+word.substring(place+1, word.length)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
