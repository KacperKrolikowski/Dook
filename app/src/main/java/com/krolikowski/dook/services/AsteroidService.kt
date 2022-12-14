package com.krolikowski.dook.services

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleService
import com.krolikowski.dook.main.MainActivity
import com.krolikowski.dook.main.MainViewModel
import com.krolikowski.dook.networking.NasaRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AsteroidService : LifecycleService() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var nasaRepository: NasaRepository

    override fun onCreate() {
        super.onCreate()

        mainViewModel = MainViewModel(nasaRepository)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        mainViewModel.counterAsteroid.observe(this) { asteroids: Int ->
            showNotification(asteroids)
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(asteroids: Int){
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = Notification
            .Builder(this, "123")
            .setContentText(asteroids.toString())
            .setContentIntent(pendingIntent)
            .build()

        startForeground(123, notification)
    }
}