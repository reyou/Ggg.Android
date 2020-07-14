package com.readendless.learning.understandinglifecycleinandroid

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

public class CustomObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun onCreateEvent() {
        Log.d("TAG", "ON_CREATE has been emitted");
    }
}