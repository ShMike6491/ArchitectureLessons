package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.repo

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo

class RepoPagePresenter(private val repo: Repo) : MvpPresenter<RepoPageView>() {
    override fun onFirstViewAttach() {
        repo.name?.let { viewState.setTitle(it) }
    }
}

@AddToEndSingle
interface RepoPageView : MvpView {
    fun setTitle(txt: String)
}