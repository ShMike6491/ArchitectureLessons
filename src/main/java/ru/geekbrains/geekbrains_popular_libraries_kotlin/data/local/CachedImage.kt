package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_image")
data class CachedImage(
    @PrimaryKey val url: String,
    var localPath: String
)