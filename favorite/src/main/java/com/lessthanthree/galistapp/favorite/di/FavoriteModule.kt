package com.lessthanthree.galistapp.favorite.di

import com.lessthanthree.galistapp.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoriteModule {
    val favoriteModule = module {
        viewModel {
            FavoriteViewModel(get())
        }
    }
}