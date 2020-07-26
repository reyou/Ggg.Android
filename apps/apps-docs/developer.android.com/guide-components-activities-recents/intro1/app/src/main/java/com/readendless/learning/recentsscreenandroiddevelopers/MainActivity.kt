package com.readendless.learning.recentsscreenandroiddevelopers

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        var KEY_EXTRA_NEW_DOCUMENT_COUNTER = "KEY_EXTRA_NEW_DOCUMENT_COUNTER"
    }

    private var documentCounter = 0
    private var useMultipleTasks = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenDocument.setOnClickListener {
            createNewDocument()
        }
        createShortcut(this)
    }

    private fun createShortcut(context: MainActivity) {
        val shortcutManager: ShortcutManager? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    getSystemService<ShortcutManager>(ShortcutManager::class.java)
                } else {
                    Log.i("createShortcut", "VERSION.SDK_INT < N_MR1")
                    null
                }
            } else {
                Log.i("createShortcut", "VERSION.SDK_INT < M")
                null
            }

        val shortcut = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutInfo.Builder(context, "id1")
                .setShortLabel("Website")
                .setLongLabel("Open the website")
                .setIcon(Icon.createWithResource(context, R.drawable.icon_website))
                .setIntent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.mysite.example.com/")
                    )
                )
                .build()
        } else {
            Log.i("createShortcut", "VERSION.SDK_INT < N_MR1")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            shortcutManager!!.dynamicShortcuts = listOf<ShortcutInfo>(shortcut as ShortcutInfo)
        }

    }

    private fun createNewDocument() {
        val newDocumentIntent = newDocumentIntent()
        if (useMultipleTasks) {
            newDocumentIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        }
        startActivity(newDocumentIntent)
        textViewNumberOfDocumentOpened.text = "Number of document opened: $documentCounter"
    }

    private fun newDocumentIntent(): Intent =
        Intent(this, NewDocumentActivity::class.java).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        android.content.Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS
            )
            putExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, documentCounter++)
        }
}