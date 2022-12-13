package com.krolikowski.dook.details

import com.krolikowski.dook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(

) : BaseViewModel<DetailsViewEvent, DetailsViewState>() {
    override fun onViewEvent(viewEvent: DetailsViewEvent) {

    }
}