package com.readendless.fragments.programmaticfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                val fragment = ExampleFragment()
                add(R.id.fragment_container, fragment)
                commit()
            }
        }
    }
}