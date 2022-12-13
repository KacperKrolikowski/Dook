package com.krolikowski.dook.list

import com.krolikowski.dook.base.BaseViewEvent

sealed class ListViewEvent : BaseViewEvent {
    data class GetImages(val imagesCount: Int) : ListViewEvent()
    object CancelRequest : ListViewEvent()
}