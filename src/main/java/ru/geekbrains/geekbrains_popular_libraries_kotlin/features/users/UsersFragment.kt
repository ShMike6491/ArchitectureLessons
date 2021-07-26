package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users

import android.os.Bundle
import android.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUsersBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.AndroidScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {
    private var binding: FragmentUsersBinding? = null
    private val b get() = binding!!

    private val presenter by moxyPresenter { UsersPresenter(
        AndroidSchedulers.mainThread(),
        App.instance.repository(),
        App.instance.router,
        AndroidScreens()
    ) }
    private var adapter: UsersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersBinding.bind(view)
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        adapter = UsersAdapter(presenter.usersListPresenter, GlideImageLoader())
        b.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}