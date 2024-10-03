package com.lessthanthree.galistapp

import android.app.Application
import com.lessthanthree.galistapp.core.di.CoreModule.databaseModule
import com.lessthanthree.galistapp.core.di.CoreModule.networkModule
import com.lessthanthree.galistapp.core.di.CoreModule.repositoryModule
import com.lessthanthree.galistapp.di.AppModule.useCaseModule
import com.lessthanthree.galistapp.di.AppModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}