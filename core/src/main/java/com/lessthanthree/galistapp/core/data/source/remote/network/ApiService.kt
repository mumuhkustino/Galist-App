package com.lessthanthree.galistapp.core.data.source.remote.network

import com.lessthanthree.galistapp.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getList(@Query("key") key: String, @Query("ordering") type: String): ListGameResponse
}