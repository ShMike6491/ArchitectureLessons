package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import android.content.Context
import androidx.room.Room
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

        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getInstance() = INSTANCE ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context!!, LocalDatabase::class.java, DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}