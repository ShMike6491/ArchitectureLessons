package ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User

interface IScreens {
    fun users(): Screen
    fun details(user: User): Screen
    fun convertor(): Screen
}