package com.krolikowski.dook.networking.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageEntity(
    val copyright: String,
    val imageUrl: String,
    val date: String,
    val title: String,
    val description: String
) : Parcelable