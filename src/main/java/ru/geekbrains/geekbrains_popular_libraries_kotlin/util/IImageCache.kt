package ru.geekbrains.geekbrains_popular_libraries_kotlin.util

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun getImageBytes(url: String): Single<ByteArray>
    fun cacheImage(url: String, bytes: ByteArray?): Completable
}