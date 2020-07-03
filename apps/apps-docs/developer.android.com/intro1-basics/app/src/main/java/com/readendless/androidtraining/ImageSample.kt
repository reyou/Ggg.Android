package com.readendless.androidtraining

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_sample.*

class ImageSample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_sample)
    }

    fun setResourceImage(view: View) {
        resourceImageView.setImageResource(R.drawable.flower)
    }
}