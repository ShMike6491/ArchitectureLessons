package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.IRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.GitHubService
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.INetworkStatus
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun usersRepo(
        api: GitHubService,
        networkStatus: INetworkStatus,
        db: LocalDatabase
    ): IRepository =
        Repository(api, networkStatus, db)
}