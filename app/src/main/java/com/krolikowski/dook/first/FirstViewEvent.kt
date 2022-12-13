package com.krolikowski.dook.first

import com.krolikowski.dook.base.BaseViewEvent

sealed class FirstViewEvent : BaseViewEvent {
    data class GetImages(val imagesCount: Int) : FirstViewEvent()
}