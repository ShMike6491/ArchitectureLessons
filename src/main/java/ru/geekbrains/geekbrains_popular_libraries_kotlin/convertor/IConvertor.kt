package ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor

import android.net.Uri
import io.reactivex.rxjava3.core.Completable

interface IConvertor {
    fun convert(imageResource: IImage) : Completable
}

interface IImage {
    fun getPath(): Uri
}