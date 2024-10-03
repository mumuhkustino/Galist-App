package com.lessthanthree.galistapp.core.utils

import com.lessthanthree.galistapp.core.data.source.local.entity.GameEntity
import com.lessthanthree.galistapp.core.data.source.remote.response.GameResponse
import com.lessthanthree.galistapp.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingsCount = it.ratingsCount,
                added = it.added,
                playtime = it.playtime,
                suggestionsCount = it.suggestionsCount,
                reviewsCount = it.reviewsCount,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingsCount = it.ratingsCount,
                added = it.added,
                playtime = it.playtime,
                suggestionsCount = it.suggestionsCount,
                reviewsCount = it.reviewsCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        name = input.name,
        released = input.released,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingsCount = input.ratingsCount,
        added = input.added,
        playtime = input.playtime,
        suggestionsCount = input.suggestionsCount,
        reviewsCount = input.reviewsCount,
        isFavorite = input.isFavorite
    )
}