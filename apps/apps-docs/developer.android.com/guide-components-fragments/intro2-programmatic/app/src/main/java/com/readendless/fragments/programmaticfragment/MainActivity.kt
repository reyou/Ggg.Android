package com.readendless.fragments.programmaticfragment

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : OnMessageReadListener, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            var messageFragment = MessageFragment();

            supportFragmentManager.beginTransaction().apply {
                this.add(R.id.fragment_container, messageFragment)
                this.commit()
            }
        }
    }

    override fun onMessageRead(message: String) {
        txt_display_message.text = message
    }
}