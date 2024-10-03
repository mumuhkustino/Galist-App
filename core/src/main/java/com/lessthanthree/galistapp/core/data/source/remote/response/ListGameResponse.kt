package com.lessthanthree.galistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(
    @field:SerializedName("error")
    val error: String,
    @field:SerializedName("results")
    val results: List<GameResponse>,
)