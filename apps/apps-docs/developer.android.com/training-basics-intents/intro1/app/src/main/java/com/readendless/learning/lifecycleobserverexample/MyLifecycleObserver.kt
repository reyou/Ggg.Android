package com.readendless.learning.lifecycleobserverexample


import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class MyLifecycleObserver(private val registry : ActivityResultRegistry)
    : DefaultLifecycleObserver {

    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", ActivityResultContracts.GetContent()) { uri ->
            run {
                println(uri)
            }
        }
    }

    fun selectImage() {
        getContent.launch("image/*")
    }
}