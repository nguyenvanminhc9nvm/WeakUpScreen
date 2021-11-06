package com.example.weakupscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder().build()

        val triggerWeakUpScreen = PeriodicWorkRequestBuilder<WorkerWeakUpScreen>(15, TimeUnit.MINUTES).setConstraints(
            constraints
        ).build()
        val doWork = WorkManager.getInstance(this)
        doWork.enqueue(triggerWeakUpScreen)
        doWork.getWorkInfoByIdLiveData(triggerWeakUpScreen.id).observe(this) {
            println("#doWeakUpScreen")
        }
    }
}