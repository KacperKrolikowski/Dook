package com.krolikowski.dook.networking

import com.krolikowski.dook.networking.entities.ImageEntity
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val nasaAPI: NasaAPI
) {
    suspend fun getImagesFromNasa(imagesCount: Int) : List<ImageEntity> {
        return nasaAPI.getNasaPictures(imagesCount).map {
            it.toEntity()
        }
    }
}