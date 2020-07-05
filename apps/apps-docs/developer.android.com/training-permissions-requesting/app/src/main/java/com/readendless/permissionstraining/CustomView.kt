package com.readendless.permissionstraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CustomView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        AudioPlayerLifecycleObserver().registerLifecycle(lifecycle)
    }
}