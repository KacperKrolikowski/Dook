package com.krolikowski.dook.networking

import com.krolikowski.dook.networking.responses.ImagesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {

    @GET("/planetary/apod")
    suspend fun getNasaPictures(
        @Query(QUERY_COUNT) count: Int
    ) : ImagesListResponse

    companion object {
        private const val QUERY_COUNT = "count"
    }
}