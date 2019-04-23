package com.example.galleryapp.withFrag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.example.galleryapp.R
import kotlinx.android.synthetic.main.without_framgent_photo_view.*


class RestFragment : Fragment() {


    private var testRate = 0.0
    //private var position = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rest, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*val extra = intent.getStringExtra("descr")
        textView.text = extra

        val rate = intent.getDoubleExtra("rate", 0.0)
        ratingBar.rating = rate.toFloat()

        val positiontest = intent.getIntExtra("positioner", 0)
        position=positiontest*/


    }

    /*fun onRespond(view: View) {
        val myintent = Intent()
        myintent.putExtra("rbresult", testRate)
        //myintent.putExtra("position", position)
        setResult(Activity.RESULT_OK, myintent)
        finish()
    }*/
}