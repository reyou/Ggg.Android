package com.readendless.startingoneactivityfromanother

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signInAndCloseClick(view: View) {
        val output = Intent()
        output.putExtra("signInResult", "Qqq signed in successfully!")
        setResult(Activity.RESULT_OK, output)
        finish()
    }
}