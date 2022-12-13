package com.krolikowski.dook.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.core.view.isVisible
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.krolikowski.dook.R
import com.krolikowski.dook.base.BaseActivity
import com.krolikowski.dook.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewState, MainViewEvent, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun handleViewState(viewState: MainViewState) {}
}