package com.readendless.androidtraining

import android.Manifest
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

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


    fun cameraPreview(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) === PackageManager.PERMISSION_GRANTED) {
                showCameraPreview()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(
                        this,
                        "Camera permission is needed to show the camera preview.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                // Request Camera permission
                val CAMERA_REQUEST_CODE = 100
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
            }
        }
    }

    private fun showCameraPreview() {
        TODO("Not yet implemented")
    }

    fun listPermissions(view: View) {
        val packageInfo: PackageInfo = super.getPackageManager().getPackageInfo(
            "com.readendless.androidtraining",
            PackageManager.GET_PERMISSIONS
        )

        val requestedPermissions: Array<String> = packageInfo.requestedPermissions;
        val granted = mutableListOf<String>();

        requestedPermissions.forEachIndexed { index, _ ->
            run {
                if (packageInfo.requestedPermissionsFlags[index] != 0
                    && PackageInfo.REQUESTED_PERMISSION_GRANTED != 0
                ) {
                    granted.add(packageInfo.requestedPermissions[index]);
                }
            }
        }

        for (grantedPermission in granted) {
            textViewPermissions.text = "${textViewPermissions.text} $grantedPermission, "
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