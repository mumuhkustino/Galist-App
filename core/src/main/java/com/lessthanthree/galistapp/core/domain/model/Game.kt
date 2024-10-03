package com.lessthanthree.galistapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Game (
    val gameId: String,
    val name: String,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double,
    val ratingsCount: Int,
    val added: Int,
    val playtime: Int,
    val suggestionsCount: Int,
    val reviewsCount: Int,
    val isFavorite: Boolean
) : Parcelable