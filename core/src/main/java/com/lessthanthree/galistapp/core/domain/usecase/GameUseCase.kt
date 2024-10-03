package com.lessthanthree.galistapp.core.domain.usecase

import com.lessthanthree.galistapp.core.data.source.Resource
import com.lessthanthree.galistapp.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {

    fun getAllGame(): Flow<Resource<List<Game>>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)

}