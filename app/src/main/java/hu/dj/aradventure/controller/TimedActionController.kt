package hu.dj.aradventure.controller

import android.os.Handler
import android.os.Looper

class TimedActionController() {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    fun runAfterDelay(delayMillis: Long, action: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            action.invoke()
        }, delayMillis)
    }

    fun runRepeatedly(intervalMillis: Long, action: () -> Unit) {
        runnable = object : Runnable {
            override fun run() {
                action.invoke()
                handler.postDelayed(this, intervalMillis)
            }
        }
        handler.postDelayed(
            runnable, intervalMillis
        )
    }

    fun stopRunning() {
        handler.removeCallbacks(runnable)
    }
}