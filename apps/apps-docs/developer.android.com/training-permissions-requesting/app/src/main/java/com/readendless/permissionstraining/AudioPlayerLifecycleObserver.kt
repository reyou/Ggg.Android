package com.readendless.permissionstraining

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

public class AudioPlayerLifecycleObserver : LifecycleObserver {
    
    private var actionHandler: ViewActionHandler? = null

    fun registerActionHandler(handler: ViewActionHandler) {
        this.actionHandler = handler
    }

    fun registerLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.e("TAG", "================================>>>> lifecycle owner STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.e("TAG", "================================>>>> lifecycle owner STOPED")
    }
}