package com.lessthanthree.galistapp.core.data.source.remote

import android.util.Log
import com.lessthanthree.galistapp.core.data.source.remote.network.ApiResponse
import com.lessthanthree.galistapp.core.data.source.remote.network.ApiService
import com.lessthanthree.galistapp.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGame(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getList("a89cd1f6dd9340848b2ec020ad947985", "")
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}