package com.readendless.activitylifecycleexample

import android.content.AsyncQueryHandler
import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStop() {
        super.onStop()

        // save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        val values = ContentValues().apply {
            put(NotePad.COLUMN_NAME_TITLE, "Current Note Title")
            put(NotePad.COLUMN_NAME_NOTE, "Current Note Text")
        }

        // do this update in background on an AsyncQueryHandler or equivalent
        val token = 0
        val uri = null
        AsyncQueryHandler().startUpdate(
                token,     // int token to correlate calls
                null,      // cookie, not used here
                uri,       // The URI for the note to update.
                values,    // The map of column names and new values to apply to them.
                null,      // No SELECT criteria are used.
                null       // No WHERE columns are used.
        )
    }

}