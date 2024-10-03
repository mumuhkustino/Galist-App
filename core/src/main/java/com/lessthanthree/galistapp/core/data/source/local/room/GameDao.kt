package com.lessthanthree.galistapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lessthanthree.galistapp.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTourism(game: List<GameEntity>)

    @Update
    fun updateFavoriteTourism(game: GameEntity)
}