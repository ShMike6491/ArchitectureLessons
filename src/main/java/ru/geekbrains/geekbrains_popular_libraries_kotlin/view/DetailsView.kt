package ru.geekbrains.geekbrains_popular_libraries_kotlin.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface DetailsView : MvpView {
    fun showUserData(text: String)
}