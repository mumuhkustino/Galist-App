package com.lessthanthree.galistapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("released")
    val released: String?,
    @field:SerializedName("background_image")
    val backgroundImage: String?,
    @field:SerializedName("rating")
    val rating: Double,
    @field:SerializedName("ratings_count")
    val ratingsCount: Int,
    @field:SerializedName("added")
    val added: Int,
    @field:SerializedName("playtime")
    val playtime: Int,
    @field:SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @field:SerializedName("reviews_count")
    val reviewsCount: Int
)