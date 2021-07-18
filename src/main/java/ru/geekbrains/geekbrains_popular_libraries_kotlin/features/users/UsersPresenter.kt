package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.UsersStore
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.list.IItemView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.list.IUserItemView

class UsersPresenter(
    private val usersRepo: UsersStore,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()
    private lateinit var usersList: List<User>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        usersRepo.getObservable().subscribe({ newList ->
            usersList = newList
            loadData()
        }, { error ->
            error.printStackTrace()
        })

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.details(usersList[itemView.pos]))
        }
    }

    private fun loadData() {
        usersListPresenter.users.addAll(usersList)
        viewState.updateList()
    }

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
            view.setLogin(user.login)
        }
    }
}

@AddToEndSingle
interface UsersView : MvpView {
    fun init()
    fun updateList()
}

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<IUserItemView>