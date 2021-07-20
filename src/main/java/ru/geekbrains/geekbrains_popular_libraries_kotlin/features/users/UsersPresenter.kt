package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.IRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.shared.IItemView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.shared.IListPresenter

class UsersPresenter(
    private val mainThread: Scheduler,
    private val repo: IRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()
    private lateinit var usersList: List<User>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.details(usersList[itemView.pos]))
        }
    }

    private fun loadData() = repo.getUsers().observeOn(mainThread)
        .subscribe({ newList ->
            usersList = newList
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(newList)
            viewState.updateList()
        }, { error ->
            error.printStackTrace()
        })

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<User>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatar?.let { view.loadAvatar(it) }
        }
    }
}

@AddToEndSingle
interface UsersView : MvpView {
    fun init()
    fun updateList()
}

interface IUserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}

interface IUserListPresenter : IListPresenter<IUserItemView>