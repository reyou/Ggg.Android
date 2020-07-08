package com.readendless.startingoneactivityfromanother

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signInClick(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun sendEmailMessage(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.action = Intent.ACTION_SEND
        val recipientArray: Array<String> = arrayOf("example@localhost.com")
        intent.putExtra(Intent.EXTRA_EMAIL, recipientArray)
        intent.putExtra(android.content.Intent.EXTRA_CC, "");
        intent.putExtra(android.content.Intent.EXTRA_BCC, "");
        intent.putExtra(
            android.content.Intent.EXTRA_SUBJECT,
            "Playlist Details"
        );
        intent.putExtra(Intent.EXTRA_TEXT, "This is body?");
        // intent.type = "message/rfc822"
        intent.type = "text/html";
        startActivity(Intent.createChooser(intent, "Send Email Using: "))
    }

    companion object {
        const val PICK_CONTACT_REQUEST = 1
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 2
        const val SIGN_IN_INTENT = 3
    }

    fun pickAContact(view: View) {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(
            Intent.createChooser(intent, "Pick an application: "),
            PICK_CONTACT_REQUEST
        )
    }

    fun openActivityForResult(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivityForResult(intent, SIGN_IN_INTENT)
    }

    fun startMyActivity(view: View) {
        val intent = Intent(this, MyActivity::class.java).apply {
            putExtra("media_id", "a1b2c3")
            // ...
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        when (requestCode) {
            SIGN_IN_INTENT -> {
                var intentMessage = intent?.getStringExtra("signInResult")
                Toast.makeText(
                    this,
                    "Sign in completed: $intentMessage",
                    Toast.LENGTH_LONG
                ).show()
            }
            PICK_CONTACT_REQUEST ->
                if (resultCode == RESULT_OK) {
                    if (intent != null && intent.data != null) {

                        val checkSelfPermission = ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_CONTACTS
                        )
                        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
                            val data: Uri? = intent.data

                            val id: String? = data?.lastPathSegment

                            val query: Cursor? = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                arrayOf(id), null
                            )
                            if (query != null) {
                                val phoneIdx =
                                    query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                if (query.moveToFirst()) {
                                    val phoneNumber: String = query.getString(phoneIdx)
                                    Toast.makeText(
                                        this,
                                        "Phone number: $phoneNumber",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                            // startActivity(Intent(Intent.ACTION_VIEW, intent.data))
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(
                                    arrayOf(Manifest.permission.READ_CONTACTS),
                                    PERMISSIONS_REQUEST_READ_CONTACTS
                                )
                            }
                        }
                    }
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    "Thanks for granting the permissions! You can try again now!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Until you grant the permission, we cannot display the names.",
                    Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}