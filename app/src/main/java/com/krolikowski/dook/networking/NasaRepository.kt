package com.krolikowski.dook.networking

import com.krolikowski.dook.networking.entities.ImageEntity
import javax.inject.Inject
import kotlin.random.Random

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

    suspend fun getAsteroidsCount() : Result<Int> {
        val asteroidCounts = kotlin.runCatching {
            val day = Random.nextInt(1, 30)
            nasaAPI.getAsteroidsData("2022-11-$day")
        }

        return asteroidCounts.mapCatching {
            it.asteroidsCount
        }
    }
}