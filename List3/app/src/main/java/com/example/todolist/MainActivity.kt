package com.example.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("1 EL")
        list.add("2 EL")
        list.add("3 EL")

    }

    fun taskAdder(view: View) {
        //list.add(task)
        //list.add("XD")
        var oneTask = writeTaskETP.text
        list.add(oneTask.toString())
        //LVPort
    }

}
