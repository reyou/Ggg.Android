package com.readendless.learning.recentsscreenandroiddevelopers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DocumentCentricActivity : AppCompatActivity() {
    companion object {
        var KEY_EXTRA_NEW_DOCUMENT_COUNTER = "KEY_EXTRA_NEW_DOCUMENT_COUNTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_centric)
    }
}