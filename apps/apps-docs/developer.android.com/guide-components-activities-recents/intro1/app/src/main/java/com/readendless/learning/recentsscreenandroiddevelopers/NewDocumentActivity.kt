package com.readendless.learning.recentsscreenandroiddevelopers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class NewDocumentActivity : AppCompatActivity() {
    private var documentCount = 0
    private lateinit var documentCounterTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_document)
        documentCount = intent
            .getIntExtra(DocumentCentricActivity.KEY_EXTRA_NEW_DOCUMENT_COUNTER, 0)
        documentCounterTextView = findViewById<TextView>(R.id.hello_new_document_text_view)
        setDocumentCounterText(R.string.hello_new_document_counter)
    }

    private fun setDocumentCounterText(helloNewDocumentCounter: Int) {

    }

    override fun onNewIntent(newIntent: Intent) {
        super.onNewIntent(newIntent)
        /* If FLAG_ACTIVITY_MULTIPLE_TASK has not been used, this Activity
        will be reused. */
        setDocumentCounterText(R.string.reusing_document_counter)
    }
}