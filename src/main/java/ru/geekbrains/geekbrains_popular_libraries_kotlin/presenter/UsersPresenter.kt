package ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.model.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.model.UsersStore
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter.list.IUserListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.UsersView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.list.IUserItemView

class UsersPresenter(
    private val usersRepo: UsersStore,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()
    private val usersList = usersRepo.getUsers()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.details(usersList[itemView.pos]))
        }
    }

    private fun loadData() {
        val users = usersList
        usersListPresenter.users.addAll(users)
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