package com.lessthanthree.galistapp.core.data.source.local

import com.lessthanthree.galistapp.core.data.source.local.entity.GameEntity
import com.lessthanthree.galistapp.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteTourism()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertTourism(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteTourism(game)
    }
}