package com.krolikowski.dook.networking.responses


import com.google.gson.annotations.SerializedName
import com.krolikowski.dook.networking.entities.ImageEntity

data class ImageResponse(
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
) {
    fun toEntity(): ImageEntity = ImageEntity(
        copyright = this.copyright ?: "",
        imageUrl = this.url,
        date = this.date,
        title = this.title
    )
}