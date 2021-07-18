package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.activity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens

class MainPresenter(private val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.convertor())
    }

    fun backClicked() {
        router.exit()
    }
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {}