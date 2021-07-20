package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.list

interface IUserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}