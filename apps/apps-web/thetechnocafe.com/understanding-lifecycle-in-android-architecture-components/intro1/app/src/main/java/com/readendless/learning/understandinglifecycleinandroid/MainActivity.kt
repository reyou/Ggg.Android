package com.readendless.learning.understandinglifecycleinandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mTextView = findViewById<TextView>(R.id.textView)
        var mButton = findViewById<Button>(R.id.button)
        var mMutableLiveData = MutableLiveData<String>()
    }
}