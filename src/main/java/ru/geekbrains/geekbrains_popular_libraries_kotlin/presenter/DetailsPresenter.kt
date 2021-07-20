package ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter

import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.model.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.DetailsView

class DetailsPresenter(private val user: User) : MvpPresenter<DetailsView>() {
    override fun onFirstViewAttach() {
        viewState.showUserData(user.login)
    }
}