package com.lessthanthree.galistapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lessthanthree.galistapp.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getAllGame().asLiveData()
}