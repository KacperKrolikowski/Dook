package com.krolikowski.dook.main

import com.krolikowski.dook.base.BaseViewEvent

sealed class MainViewEvent : BaseViewEvent {
    object GetAsteroidsData : MainViewEvent()
    object StopAsteroidsData : MainViewEvent()
}