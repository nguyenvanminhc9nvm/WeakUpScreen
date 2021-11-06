package com.example.weakupscreen

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.os.PowerManager

import android.os.PowerManager.WakeLock


class WorkerWeakUpScreen(
    private val context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        val pm = (context.getSystemService(Context.POWER_SERVICE) as PowerManager)
        val wl = pm.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "WeakUpScreen::MyWakelockTag"
        )
        wl.acquire(2000)
        wl.release()
        return Result.success()
    }
}