package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.activity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView