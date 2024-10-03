package com.lessthanthree.galistapp.core.di

import androidx.room.Room
import com.lessthanthree.galistapp.core.data.source.GameRepository
import com.lessthanthree.galistapp.core.data.source.local.LocalDataSource
import com.lessthanthree.galistapp.core.data.source.local.room.GameDatabase
import com.lessthanthree.galistapp.core.data.source.remote.RemoteDataSource
import com.lessthanthree.galistapp.core.data.source.remote.network.ApiService
import com.lessthanthree.galistapp.core.domain.repository.IGameRepository
import com.lessthanthree.galistapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {
    val databaseModule = module {
        factory { get<GameDatabase>().gameDao() }
        single {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("lessthanthree".toCharArray())
            val factory = SupportFactory(passphrase)
            Room.databaseBuilder(
                androidContext(),
                GameDatabase::class.java, "Game.db"
            )
                .openHelperFactory(factory)
                .fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Ti5AhU6OcJWgTQfedHg7+xEaS6XE1iOgqkeZX161P7U=")
            .add(hostname, "sha256/yDu9og255NN5GEf+Bwa9rTrqFQ0EydZ0r1FCh9TdAW4=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .add(hostname, "sha256/K87oWBWM9UZfyddvDfoxL+8lpNyoUB2ptGtn0fv6G2Q=")
            .add(hostname, "sha256/b0kr6GUvjgiM8He9NBD+PpV3XaC8gCh9D+sTVN/HAbU=")
            .add(hostname, "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4=")
            .add(hostname, "sha256/mEflZT5enoR1FuXLgYYGqnVEoZvmf9c2bVBpiOjYQ0c=")
            .build()
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IGameRepository> {
            GameRepository(
                get(),
                get(),
                get()
            )
        }
    }


}