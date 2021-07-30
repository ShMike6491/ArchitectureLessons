package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.IRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.shared.IItemView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.shared.IListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import javax.inject.Inject

class DetailsPresenter(
    private val user: User,
) : MvpPresenter<DetailsView>() {
    @Inject lateinit var repository: IRepository
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var mainThread: Scheduler

    val listPresenter = DetailsListPresenter()
    private lateinit var repoList: List<Repo>

    override fun onFirstViewAttach() {
        viewState.init(user)
        loadData(user)
        listPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.repoPage(repoList[itemView.pos]))
        }
    }

    private fun loadData(user: User) = repository.getUserRepos(user)
        .observeOn(mainThread)
        .subscribe({ newList ->
            repoList = newList
            listPresenter.repos.clear()
            listPresenter.repos.addAll(newList)
            viewState.updateList()
        }, { error ->
            error.printStackTrace()
        })

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class DetailsListPresenter : IDetailsListPresenter {
        val repos = mutableListOf<Repo>()

        override var itemClickListener: ((IDetailItemView) -> Unit)? = null

        override fun bindView(view: IDetailItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setTitle(it) }
            repo.description?.let { view.setDescription(it) }
            repo.stars?.let { view.setStars(it) }
            repo.language?.let { view.setLanguage(it) }
        }

        override fun getCount() = repos.size
    }
}

@AddToEndSingle
interface DetailsView : MvpView {
    fun init(user: User)
    fun updateList()
}

interface IDetailItemView : IItemView {
    fun setTitle(txt: String)
    fun setDescription(txt: String)
    fun setStars(count: Int)
    fun setLanguage(txt: String)
}

interface IDetailsListPresenter : IListPresenter<IDetailItemView>