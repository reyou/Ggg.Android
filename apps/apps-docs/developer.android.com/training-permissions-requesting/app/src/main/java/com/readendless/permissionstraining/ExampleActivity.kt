package com.readendless.permissionstraining

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_example.*
import java.util.*

class ExampleActivity : AppCompatActivity() {
    private var gameState = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ACTIVITY_LIFECYCLE", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        if (savedInstanceState == null) {
            Log.i("ACTIVITY_LIFECYCLE", "onCreate savedInstanceState IS NULL")
        } else {
            Log.i("ACTIVITY_LIFECYCLE", "onCreate savedInstanceState IS NOT NULL")
        }

        val time = Calendar.getInstance().time;

        gameState = "Game created at $time"
        textView.text = gameState
    }

    override fun onResume() {
        Log.i("ACTIVITY_LIFECYCLE", "onResume")
        super.onResume()

    }

    companion object {
        val TEXT_VIEW_KEY = "TEXT_VIEW_KEY"
        val GAME_STATE_KEY = "GAME_STATE_KEY"
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("ACTIVITY_LIFECYCLE", "onSaveInstanceState")
        outState?.run {
            putString(GAME_STATE_KEY, "$gameState onSaveInstanceState")
            putString(TEXT_VIEW_KEY, textView.text.toString())
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }

    // This callback is called only when there is a saved instance that is previously saved by using
// onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
// other state here, possibly usable after onStart() has completed.
// The savedInstanceState Bundle is same as the one used in onCreate().
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.i("ACTIVITY_LIFECYCLE", "onRestoreInstanceState")
        textView.text = savedInstanceState?.getString(GAME_STATE_KEY)
    }


}