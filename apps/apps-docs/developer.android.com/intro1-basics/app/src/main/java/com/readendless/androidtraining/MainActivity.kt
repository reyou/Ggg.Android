package com.readendless.androidtraining

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MESSAGE = "com.readendless.androidtraining"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_main)
        Log.i("ANDROID_TRAINING", "MainActivity STARTED.")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // super.getMenuInflater()
        menuInflater.inflate(R.menu.example_menu, menu)
        return true
    }

    fun onGroupItemClick(item: MenuItem) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by onOptionsItemSelected
        // <code><a href="/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem)">onOptionsItemSelected()</a></code>
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, newConfig.orientation, Toast.LENGTH_SHORT).show()
        }
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(Companion.EXTRA_MESSAGE, message)
        }
        super.startActivity(intent)
    }

    fun openImageSample(view: View) {
        val intent = Intent(this, ImageSample::class.java);
        super.startActivity(intent);
    }

}