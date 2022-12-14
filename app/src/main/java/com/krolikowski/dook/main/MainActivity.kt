package com.krolikowski.dook.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.krolikowski.dook.R
import com.krolikowski.dook.base.BaseActivity
import com.krolikowski.dook.databinding.ActivityMainBinding
import com.krolikowski.dook.services.AsteroidService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewState, MainViewEvent, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun handleViewState(viewState: MainViewState) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val config = AppBarConfiguration(navController.graph)

        setSupportActionBar(binding.toolBar)

        binding.toolBar.setupWithNavController(navController, config)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.enable_asteroid_alarm -> {
                true
            }
            R.id.start_asteroid_alarm -> {
                startService(Intent(this, AsteroidService::class.java))
                viewModel.onViewEvent(MainViewEvent.GetAsteroidsData)
                true

            }
            R.id.stop_asteroid_alarm -> {
                startForegroundService(Intent(this, AsteroidService::class.java))
                viewModel.onViewEvent(MainViewEvent.StopAsteroidsData)
                true

            }
            R.id.bind_service -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}