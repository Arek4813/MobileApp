package com.example.myfirstlabapp

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var myrandom = 0
    private var attempts = 0
    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializer()
        checkButton.setOnClickListener {
            checker(Integer.parseInt(numberET.text.toString()))
            hideKeyboard()
        }
        fontButton.setOnClickListener {
            if (flag == true)
                fontChanger()
            else
                fontBacker()
        }
        newGameButton.setOnClickListener {
            attempts=0
            initializer()
            resultTV.text = ""
            advicesTV.text = ""
            numberET.text.clear()
        }
    }

    fun initializer() {
        val r = Random()
        myrandom = r.nextInt(451)
        attemptsTV.text = "Your attempts: $attempts"

    }

    fun checker(userVar: Int) {
        attempts++
        attemptsTV.text = "Your attempts: $attempts"
        if(userVar==myrandom) {
            resultTV.text = "YES! YOU GUESSED CORRECT NUMBER!"
            advicesTV.text = "CONGRATULATIONS!!!"
        }
        else {
            resultTV.text = "NO! UNFORTUNATELY YOU MISSED!"
            if(userVar>myrandom)
                advicesTV.text = "GIVE LESS NUMBER"
            else
                advicesTV.text = "GIVE GREATER NUMBER"
        }
    }

    fun fontChanger() {
        //val typeface = Typeface.createFromAsset(applicationContext.assets, "font/roboto.ttf")
        descrTV.setTypeface(null, Typeface.BOLD_ITALIC)
        prnumbTV.setTypeface(null, Typeface.ITALIC)
        flag=false
    }

    fun fontBacker() {
        //val typeface = Typeface.createFromAsset(applicationContext.assets, "font/roboto.ttf")
        descrTV.setTypeface(null, Typeface.NORMAL)
        prnumbTV.setTypeface(null, Typeface.BOLD)
        flag=true
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
