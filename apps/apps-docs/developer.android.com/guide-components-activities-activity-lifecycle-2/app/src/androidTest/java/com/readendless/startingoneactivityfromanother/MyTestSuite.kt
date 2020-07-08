package com.readendless.startingoneactivityfromanother

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTestSuite {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MyActivity>()

    private fun <T> activityScenarioRule(): T {
        TODO("Not yet implemented")
    }

    @Test
    fun testEvent() {
        val scenario = launchActivity<MyActivity>()
    }

    private fun <T> launchActivity(): Any {
        TODO("Not yet implemented")
    }
}