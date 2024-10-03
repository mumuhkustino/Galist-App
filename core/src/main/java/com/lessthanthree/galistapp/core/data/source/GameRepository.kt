package com.lessthanthree.galistapp.core.data.source

import com.lessthanthree.galistapp.core.data.source.local.LocalDataSource
import com.lessthanthree.galistapp.core.data.source.remote.RemoteDataSource
import com.lessthanthree.galistapp.core.data.source.remote.network.ApiResponse
import com.lessthanthree.galistapp.core.data.source.remote.response.GameResponse
import com.lessthanthree.galistapp.core.domain.model.Game
import com.lessthanthree.galistapp.core.domain.repository.IGameRepository
import com.lessthanthree.galistapp.core.utils.AppExecutors
import com.lessthanthree.galistapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()


            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}