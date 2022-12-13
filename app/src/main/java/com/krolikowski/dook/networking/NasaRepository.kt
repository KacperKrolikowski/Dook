package com.krolikowski.dook.networking

import com.krolikowski.dook.networking.entities.ImageEntity
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val nasaAPI: NasaAPI
) {
    suspend fun getImagesFromNasa(imagesCount: Int) : Result<List<ImageEntity>> {
        val nasaImagesResult = kotlin.runCatching {
            nasaAPI.getNasaPictures(imagesCount)
        }

        return nasaImagesResult.mapCatching { list ->
            list.map { item ->
                item.toEntity()
            }
        }
    }
}