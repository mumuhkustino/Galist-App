package com.lessthanthree.galistapp.core.domain.usecase

import com.lessthanthree.galistapp.core.data.source.Resource
import com.lessthanthree.galistapp.core.domain.model.Game
import com.lessthanthree.galistapp.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> {
        return gameRepository.getAllGame()
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return gameRepository.getFavoriteGame()
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        gameRepository.setFavoriteGame(game, state)
    }
}