package com.readendless.lifecycleexample

import android.util.Log

class MyLogger {

    companion object {
        private const val TAG = "MyLogger"
    }

    fun logCreate() {
        Log.e(TAG, "Activity created")
    }

    fun logStart() {
        Log.e(TAG, "Activity started")
    }

    fun logResume() {
        Log.e(TAG, "Activity resumed")
    }

    fun logPause() {
        Log.e(TAG, "Activity will pause")
    }

    fun logStop() {
        Log.e(TAG, "Activity will stop")
    }

    fun logDestroy() {
        Log.d(TAG, "Activity will be destroyed")
    }
}

