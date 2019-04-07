package com.example.tasklisttodo

import android.app.Activity
import android.app.LauncherActivity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity() {

    var tasksList = ArrayList<SimpleTask>()
    var imagesList = ArrayList<Int>()
    var importanceList = ArrayList<String>()
    var path = 0
    var ctrCat = false
    var ctrTime = false
    var ctrImp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideKeyboard()
        setContentView(R.layout.activity_main)
        categoryGraphic.setImageResource(R.drawable.people)

        imagesList.add(R.drawable.people)
        imagesList.add(R.drawable.food)
        imagesList.add(R.drawable.sport)
        imagesList.add(R.drawable.book)
        imagesList.add(R.drawable.freetime)

        importanceList.add("IMPORTANT")
        importanceList.add("NORMAL")
        importanceList.add("THE LEAST VALID")

        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, importanceList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(importanceChoicer)
        {
            adapter = aa
            setSelection(0, false)
            gravity = Gravity.CENTER
        }

        GraphicSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //Toast.makeText(applicationContext, "Choose category of task", Toast.LENGTH_SHORT).show()
            }

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if(i<=20) {
                    path=0
                    categoryGraphic.setImageResource(imagesList[path])
                }
                else if(i<=40) {
                    path=1
                    categoryGraphic.setImageResource(imagesList[path])
                }
                else if(i<=60) {
                    path=2
                    categoryGraphic.setImageResource(imagesList[path])
                }
                else if(i<=80) {
                    path=3
                    categoryGraphic.setImageResource(imagesList[path])
                }
                else {
                    path = 4
                    categoryGraphic.setImageResource(imagesList[path])
                }
            }
        })

        LVPort.setOnItemLongClickListener { _,_, index, _ ->
            tasksList.removeAt(index)
            val adapterP = TaskAdapter(this, tasksList)
            LVPort.adapter = adapterP
            return@setOnItemLongClickListener true
        }

        addTaskButtonP.setOnClickListener {
            taskAdder()
            val adapterP = TaskAdapter(this, tasksList)
            LVPort.adapter = adapterP
        }

        sortValid.setOnClickListener {
            if(!ctrImp) {
                tasksList.sortWith(compareBy({ it.Importance }))
                ctrImp=true
            }
            else {
                tasksList.sortByDescending { it.Importance }
                ctrImp=false
            }
            val adapterP = TaskAdapter(this, tasksList)
            LVPort.adapter = adapterP
        }

        sortCategory.setOnClickListener {
            if(!ctrCat) {
                tasksList.sortWith(compareBy({it.ImgURL}))
                ctrCat=true
            }
            else {
                tasksList.sortByDescending { it.ImgURL }
                ctrCat=false
            }
            val adapterP = TaskAdapter(this, tasksList)
            LVPort.adapter = adapterP
        }

        sortTime.setOnClickListener {
            if(!ctrTime) {
                tasksList.sortWith(compareBy({it.Time}))
                ctrTime=true
            }
            else {
                tasksList.sortByDescending { it.Time }
                ctrTime=false
            }
            val adapterP = TaskAdapter(this, tasksList)
            LVPort.adapter = adapterP
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(tasksList!=null) {
            outState.putParcelableArrayList("list", tasksList)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            tasksList=savedInstanceState.getParcelableArrayList("list")
        };
        val adapterP = TaskAdapter(this, tasksList)
        LVPort.adapter = adapterP
    }


    fun taskAdder() {
        var oneTextTask = writeTaskETP.text
        writeTaskETP.setText("")
        var timer = Date().toLocaleString()
        var task = SimpleTask(oneTextTask.toString(), importanceChoicer.selectedItem.toString(), imagesList[path], timer)
        tasksList.add(task)
        println(tasksList)
        hideKeyboard()
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
