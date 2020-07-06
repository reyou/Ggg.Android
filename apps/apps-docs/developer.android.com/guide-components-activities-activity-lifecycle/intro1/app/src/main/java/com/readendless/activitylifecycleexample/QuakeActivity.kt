package com.readendless.activitylifecycleexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quake)
        val documentId = intent.extras?.get("documentId")
        Toast.makeText(this, "documentId: $documentId", Toast.LENGTH_LONG).show()
    }
}