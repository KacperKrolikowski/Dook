package com.krolikowski.dook.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.krolikowski.dook.base.BaseViewModel
import com.krolikowski.dook.networking.NasaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : BaseViewModel<MainViewEvent, MainViewState>() {

    private var getAsteroidsJob : Job? = null
    val counterAsteroid = MutableLiveData<Int>()

    override fun onViewEvent(viewEvent: MainViewEvent) {
        when (viewEvent) {
            is MainViewEvent.GetAsteroidsData -> {
                getAsteroids()
            }
            is MainViewEvent.StopAsteroidsData -> {
                cancelAsteroidsJob()
            }
        }
    }

    private fun getAsteroids() {
        getAsteroidsJob = viewModelScope.launch {
            while (isActive){
                nasaRepository.getAsteroidsCount()
                    .onSuccess {
                        counterAsteroid.value = it
                    }
                delay(1000 * 10)
            }
        }
    }

    private fun cancelAsteroidsJob(){
        getAsteroidsJob?.cancel()
    }
}