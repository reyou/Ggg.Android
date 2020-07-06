package com.readendless.activitylifecycleexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentScore: Int = 0
    private var currentLevel: Int = 0

    companion object {
        const val STATE_SCORE = "STATE_SCORE"
        const val STATE_LEVEL = "STATE_LEVEL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextScore.setText(currentScore.toString())
        editTextLevel.setText(currentLevel.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentScore = savedInstanceState[STATE_SCORE] as Int;
        currentLevel = savedInstanceState[STATE_LEVEL] as Int;
        editTextScore.setText(currentScore.toString())
        editTextLevel.setText(currentLevel.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the user's current game state
        outState.run {
            putInt(STATE_SCORE, currentScore)
            putInt(STATE_LEVEL, currentLevel)
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)
    }

    fun scoreUp(view: View) {
        currentScore += 1;
        editTextScore.setText(currentScore.toString())
        Toast.makeText(this, "Current score: $currentScore", Toast.LENGTH_SHORT).show()
    }

    fun levelUp(view: View) {
        currentLevel += 1;
        editTextLevel.setText(currentLevel.toString())
        Toast.makeText(this, "Current level: $currentLevel", Toast.LENGTH_SHORT).show()
    }

    fun openActivity(view: View) {
        Intent(this, QuakeActivity::class.java).apply {
            putExtra("documentId", "123456")
        }.apply {
            startActivity(this)
        }
    }

}