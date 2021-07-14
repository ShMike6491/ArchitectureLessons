package ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.MainView

class MainPresenter(private val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}