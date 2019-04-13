package com.example.galleryapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.galleryapp.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.photo_view.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var ctrSort = false;
    var PictureLists = ArrayList<Photo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        PictureLists.add(Photo("Magical abstract photo", R.drawable.image1, 0.0, 0))
        PictureLists.add(Photo("Beautiful landscape", R.drawable.image2, 0.0, 1))
        PictureLists.add(Photo("Photo from countryside, a lot of poppy on it", R.drawable.image3, 0.0, 2))
        PictureLists.add(Photo("A rose in black-white scenery", R.drawable.image4, 0.0, 3))
        PictureLists.add(Photo("Middle of Polish forest", R.drawable.image5, 0.0, 4))
        PictureLists.add(Photo("A small waterfall in evening climate", R.drawable.image6, 0.0, 5))

        clickSetter()

    }

    fun sorter(view: View) {
        if (!ctrSort) {
            PictureLists.sortWith(compareBy { it.Rating })
            ctrSort=true
            sortButton.text="SORT BY RATING DESC"
        }
        else {
            PictureLists.sortByDescending { it.Rating }
            ctrSort = false
            sortButton.text="SORT BY RATING ASC"
        }
        var i = 0
        while(i<PictureLists.size) {
            PictureLists[i].Index=i
            i++
        }
        clickSetter()
        miniaturesSetter()
    }

    fun clickSetter() {
        imageView1.setOnClickListener {
            shower(0)
        }
        imageView2.setOnClickListener {
            shower(1)
        }
        imageView3.setOnClickListener {
            shower(2)
        }
        imageView4.setOnClickListener {
            shower(3)
        }
        imageView5.setOnClickListener {
            shower(4)
        }
        imageView6.setOnClickListener {
            shower(5)
        }
    }

    fun miniaturesSetter() {
        imageView1.setImageResource(PictureLists[0].PhotoURL)
        imageView2.setImageResource(PictureLists[1].PhotoURL)
        imageView3.setImageResource(PictureLists[2].PhotoURL)
        imageView4.setImageResource(PictureLists[3].PhotoURL)
        imageView5.setImageResource(PictureLists[4].PhotoURL)
        imageView6.setImageResource(PictureLists[5].PhotoURL)
    }

    fun shower(index: Int) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra("descr", PictureLists[index].Description)
        intent.putExtra("photo", PictureLists[index].PhotoURL)
        intent.putExtra("rate", PictureLists[index].Rating)
        intent.putExtra("positioner", PictureLists[index].Index)
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==123) {
            var rating = data?.getDoubleExtra("rbresult", 0.0)
            var positioner = data?.getIntExtra("position", 0)
            if(rating!=null) {
                PictureLists[positioner!!].Rating = rating!!
            }
        }
    }

}
