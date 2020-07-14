package com.readendless.learning.lifecycleobserverexample


import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*


class MyLifecycleObserver(
    private val context: AppCompatActivity,
    private val registry: ActivityResultRegistry
) :
    LifecycleObserver {

    private lateinit var getContent: ActivityResultLauncher<String>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", ActivityResultContracts.GetContent()) { uri ->
            run {
                Toast.makeText(context, uri.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun selectImage() {
        getContent.launch("image/*")
    }
}