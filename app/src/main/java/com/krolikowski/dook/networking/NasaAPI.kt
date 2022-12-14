package com.krolikowski.dook.networking

import com.krolikowski.dook.networking.responses.AsteroidsCountResponse
import com.krolikowski.dook.networking.responses.ImagesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {

    @GET("/planetary/apod")
    suspend fun getNasaPictures(
        @Query(QUERY_COUNT) count: Int
    ) : ImagesListResponse

    @GET("/neo/rest/v1/feed")
    suspend fun getAsteroidsData(
        @Query(QUERY_DATE) date: String
    ) : AsteroidsCountResponse

    companion object {
        private const val QUERY_COUNT = "count"
        private const val QUERY_DATE = "start_date"
    }
}