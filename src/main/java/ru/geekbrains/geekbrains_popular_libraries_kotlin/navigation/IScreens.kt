package ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun details(user: ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User): Screen
    fun convertor(): Screen
}