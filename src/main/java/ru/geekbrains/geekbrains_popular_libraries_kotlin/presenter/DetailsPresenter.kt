package ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter

import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.model.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.DetailsView

class DetailsPresenter : MvpPresenter<DetailsView>() {
    fun userReceived(user: User) = viewState.showUserData(user.login)
}