package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.CountersModel
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.MainView

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun btnOneClick() {
        val nextValue = model.next(0)
        view.setButtonOneTxt(nextValue.toString())
    }

    fun btnTwoClick() {
        val nextValue = model.next(1)
        view.setButtonTwoTxt(nextValue.toString())
    }

    fun btnThreeClick() {
        val nextValue = model.next(2)
        view.setButtonThreeTxt(nextValue.toString())
    }
}