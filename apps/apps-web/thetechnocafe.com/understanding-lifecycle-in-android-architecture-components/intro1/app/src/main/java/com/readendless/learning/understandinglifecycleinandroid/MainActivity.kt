package com.readendless.learning.understandinglifecycleinandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mTextView = findViewById<TextView>(R.id.textView)
        var mButton = findViewById<Button>(R.id.button)
        var mMutableLiveData = MutableLiveData<String>()
        mMutableLiveData.observe(this,
            Observer { liveData ->
                mTextView.text = liveData
            })
        mButton.setOnClickListener {
            val random = Random()
            mMutableLiveData.value = "Random Int:" + random.nextInt()
        }
    }
}