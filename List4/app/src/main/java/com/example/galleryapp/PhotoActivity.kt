package com.example.galleryapp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.without_framgent_photo_view.*
import android.widget.RatingBar
import kotlinx.android.synthetic.main.fragment_main_photo_view1.*


class PhotoActivity : AppCompatActivity() {

    private var testRate = 0.0
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main_photo_view1)

        val extra = intent.getStringExtra("descr")
        fragment_rest.textView.text = extra

        val tmp = intent.getIntExtra("photo", R.drawable.error)
        fragment_photo.imageViewMain.setImageResource(tmp)

        val rate = intent.getDoubleExtra("rate", 0.0)
        fragment_rest.ratingBar.rating = rate.toFloat()

        val positiontest = intent.getIntExtra("positioner", 0)
        position=positiontest

        fragment_rest.ratingBar.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener{ ratingBar, rating, fromUser ->
                    testRate = rating.toDouble()
        }

    }

    fun onRespond(view: View) {
        val myintent = Intent()
        myintent.putExtra("rbresult", testRate)
        myintent.putExtra("position", position)
        setResult(Activity.RESULT_OK, myintent)
        finish()
    }


}