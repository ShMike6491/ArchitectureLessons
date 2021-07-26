package ru.geekbrains.geekbrains_popular_libraries_kotlin.util

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}