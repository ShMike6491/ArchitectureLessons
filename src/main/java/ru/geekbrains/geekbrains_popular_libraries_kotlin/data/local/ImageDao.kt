package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import androidx.room.*

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: CachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: CachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<CachedImage>)

    @Update
    fun update(image: CachedImage)

    @Update
    fun update(vararg images: CachedImage)

    @Update
    fun update(images: List<CachedImage>)

    @Delete
    fun delete(image: CachedImage)

    @Delete
    fun delete(vararg images: CachedImage)

    @Delete
    fun delete(images: List<CachedImage>)

    @Query("SELECT * FROM cached_image")
    fun getAll(): List<CachedImage>

    @Query("SELECT * FROM cached_image WHERE url = :url LIMIT 1")
    fun getImageByUrl(url: String): CachedImage?
}