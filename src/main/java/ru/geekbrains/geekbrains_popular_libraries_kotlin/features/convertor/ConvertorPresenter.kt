package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.convertor

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class ConvertorPresenter(private val router: Router) : MvpPresenter<ConvertorView>() {
    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}

@AddToEndSingle
interface ConvertorView : MvpView {}