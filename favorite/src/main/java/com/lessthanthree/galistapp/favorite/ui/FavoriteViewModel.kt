package com.lessthanthree.galistapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lessthanthree.galistapp.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGame = gameUseCase.getFavoriteGame().asLiveData()
}