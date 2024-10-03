package com.lessthanthree.galistapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    val gameId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String?,
    @ColumnInfo(name = "released")
    val released: String?,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "ratings_count")
    val ratingsCount: Int,
    @ColumnInfo(name = "added")
    val added: Int,
    @ColumnInfo(name = "playtime")
    val playtime: Int,
    @ColumnInfo(name = "suggestions_count")
    val suggestionsCount: Int,
    @ColumnInfo(name = "reviews_count")
    val reviewsCount: Int,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable