package com.lessthanthree.galistapp.di

import com.lessthanthree.galistapp.core.domain.usecase.GameInteractor
import com.lessthanthree.galistapp.core.domain.usecase.GameUseCase
import com.lessthanthree.galistapp.ui.detail.DetailGameViewModel
import com.lessthanthree.galistapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<GameUseCase> { GameInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { DetailGameViewModel(get()) }
    }
}