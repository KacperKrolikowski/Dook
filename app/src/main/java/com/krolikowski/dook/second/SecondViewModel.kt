package com.krolikowski.dook.second

import com.krolikowski.dook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(

) : BaseViewModel<SecondViewEvent, SecondViewState>() {
    override fun onViewEvent(viewEvent: SecondViewEvent) {

    }
}