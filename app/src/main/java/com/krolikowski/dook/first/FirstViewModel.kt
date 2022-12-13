package com.krolikowski.dook.first

import com.krolikowski.dook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(

) : BaseViewModel<FirstViewEvent, FirstViewState>() {
    override fun onViewEvent(viewEvent: FirstViewEvent) {

    }
}