package ru.yandex.practicum.sprint8koh08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SPRINT_8"
    }

    lateinit var timer: Timer

    lateinit var counterText: TextView

    var counter: Int = 0

    var lastOnStopTimestamp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "$this onCreate")

        counterText = findViewById(R.id.counter)


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$this onStart time=${System.currentTimeMillis()}")
        val onStartTimestamp = System.currentTimeMillis()
        val delta = onStartTimestamp - lastOnStopTimestamp
        if (delta > 3000){
            Log.d(TAG, "Time for PIN code")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$this onResume")
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                counter++
                counterText.post {
                    counterText.text = counter.toString()
                }
            }
        }, 0, 1000)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$this onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$this onStop time=${System.currentTimeMillis()}")
        timer.cancel()
        lastOnStopTimestamp = System.currentTimeMillis()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$this onDestroy")
    }
}