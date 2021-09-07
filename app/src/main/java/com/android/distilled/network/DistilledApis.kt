package com.android.distilled.network

import com.android.distilled.objects.TvShowsResponseDO
import retrofit2.http.GET
import retrofit2.http.Query

interface DistilledApis {

    @GET("tv/top_rated")
    suspend fun getShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): TvShowsResponseDO?
}