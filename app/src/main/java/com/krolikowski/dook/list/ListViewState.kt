package com.krolikowski.dook.list

import com.krolikowski.dook.base.BaseViewState
import com.krolikowski.dook.networking.entities.ImageEntity

sealed class ListViewState : BaseViewState {
    data class Success(val images: List<ImageEntity>) : ListViewState()
    object Loading: ListViewState()
    data class Error(val error: String): ListViewState()
}