package com.example.tasklisttodo

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.tasklisttodo.R.layout.one_task_file

class TaskAdapter (context: Context, var list: ArrayList<SimpleTask>) :
    ArrayAdapter<SimpleTask>(context, one_task_file, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.one_task_file, parent, false)
        }
        view!!.findViewById<TextView>(R.id.textView).text = list[position].Description
        view!!.findViewById<ImageView>(R.id.selectedGraph).setImageResource(list[position].ImgURL)
        view!!.findViewById<TextView>(R.id.textView3).text = list[position].Importance
        view!!.findViewById<TextView>(R.id.textView4).text = list[position].Time


        /*view!!.findViewById<ListView>(R.id.LVPort).setOnLongClickListener{
            list.remove(list[position])
        }*/


        return view
    }

}
