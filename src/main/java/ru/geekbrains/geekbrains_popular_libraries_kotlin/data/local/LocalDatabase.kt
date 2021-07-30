package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [LocalUser::class, LocalRepository::class, CachedImage::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}