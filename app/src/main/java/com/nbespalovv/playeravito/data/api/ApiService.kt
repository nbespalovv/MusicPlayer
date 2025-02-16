package com.nbespalovv.playeravito.data.api

import com.nbespalovv.playeravito.dto.TrackDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("chart/0/tracks")
    suspend fun loadChart(@Query("limit") limit: Int = 35): Response<TrackDto>

    @GET("search/")
    suspend fun search(@Query("q") query: String) : Response<TrackDto>
}