package com.example.galleryapp

import android.content.Context
import android.media.Image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.galleryapp.R.layout.photo_view

class PhotoAdapter (context: Context, var list: ArrayList<Photo>) :
    ArrayAdapter<Photo>(context, photo_view , list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.photo_view, parent, false)
        }
        view!!.findViewById<TextView>(R.id.textView).text = list[position].Description
        view!!.findViewById<RatingBar>(R.id.ratingBar).rating = list[position].Rating.toFloat()
        view!!.findViewById<ImageView>(R.id.imageViewMain).setImageResource(list[position].PhotoURL)

        return view
    }

}