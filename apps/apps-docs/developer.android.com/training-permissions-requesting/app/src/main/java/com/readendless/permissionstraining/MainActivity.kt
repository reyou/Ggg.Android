package com.readendless.permissionstraining

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher. You can use either a val, as shown in this snippet,
// or a lateinit var in your onAttach() or onCreate() method.
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted.
                // Continue the action or workflow in your app.
                Toast.makeText(this, "Camera Permission is granted, thanks!", Toast.LENGTH_LONG)
                    .show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(
                    this,
                    "You denied the camera permission. Sorry to hear that :(",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            run {
                when (result.resultCode) {
                    Activity.RESULT_OK -> {
                        Toast.makeText(
                            this,
                            "Activity returned OK!",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = result.data
                    }
                    Activity.RESULT_CANCELED -> {
                        Toast.makeText(
                            this,
                            "Activity CANCELLED!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Activity result code: ${result.resultCode}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    fun changeDefaultSmsHandler(view: View) {
        val setSmsAppIntent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
        setSmsAppIntent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName)
        setSmsAppIntent.apply {
            startForResult.launch(this)
        }
    }

    fun checkPermission(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    Toast.makeText(this, "Camera is allowed already!", Toast.LENGTH_LONG).show()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    Toast.makeText(
                        this,
                        "Since camera is not allowed, we cannot scan the books :(",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this,
                        "Asking for the permission...",
                        Toast.LENGTH_LONG
                    ).show()

                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )
                }
            }
        }
    }

    fun intentFilterExampleExecutor(view: View) {
        val textMessage = "This is a sample text message.";
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textMessage)
        }
        startActivity(sendIntent)
    }

    fun sendMessage(view: View) {
        val textMessage = txtSendMessage.text;
        val intent = Intent(this, ExampleActivity::class.java).apply {
            putExtra("StateMessage", textMessage)
        }
        startActivity(intent)
    }
}