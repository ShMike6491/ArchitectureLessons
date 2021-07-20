package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User

class DetailsPresenter(private val user: User) : MvpPresenter<DetailsView>() {
    override fun onFirstViewAttach() {
        viewState.showUserData(user.login)
    }
}

@AddToEndSingle
interface DetailsView : MvpView {
    fun showUserData(text: String)
}