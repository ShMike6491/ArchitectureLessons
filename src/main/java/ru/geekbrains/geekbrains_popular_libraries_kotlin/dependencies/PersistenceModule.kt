package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.ImageCacheImpl
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    fun database(app: App): LocalDatabase = Room
        .databaseBuilder(app, LocalDatabase::class.java, LocalDatabase.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun imageCacheProvider(db: LocalDatabase, app: App): IImageCache = ImageCacheImpl(db, app)
}