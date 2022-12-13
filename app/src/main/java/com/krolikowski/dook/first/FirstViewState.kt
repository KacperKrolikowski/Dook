package com.krolikowski.dook.first

import com.krolikowski.dook.base.BaseViewState
import com.krolikowski.dook.networking.entities.ImageEntity

sealed class FirstViewState : BaseViewState {
    data class Success(val images: List<ImageEntity>) : FirstViewState()
    object Loading: FirstViewState()
    data class Error(val error: String): FirstViewState()
}