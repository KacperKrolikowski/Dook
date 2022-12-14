package com.krolikowski.dook.networking.responses

import com.google.gson.annotations.SerializedName

data class AsteroidsCountResponse (
    @SerializedName("element_count")
    val asteroidsCount: Int
        )