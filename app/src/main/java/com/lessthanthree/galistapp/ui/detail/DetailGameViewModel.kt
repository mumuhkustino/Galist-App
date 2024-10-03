package com.lessthanthree.galistapp.ui.detail

import androidx.lifecycle.ViewModel
import com.lessthanthree.galistapp.core.domain.model.Game
import com.lessthanthree.galistapp.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus: Boolean) = gameUseCase.setFavoriteGame(game, newStatus)
}