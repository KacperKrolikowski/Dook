package com.krolikowski.dook.main

import com.krolikowski.dook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : BaseViewModel<MainViewEvent, MainViewState>() {
    override fun onViewEvent(viewEvent: MainViewEvent) {

    }
}