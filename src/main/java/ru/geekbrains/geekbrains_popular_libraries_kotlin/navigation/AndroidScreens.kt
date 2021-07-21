package ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.convertor.ConvertorFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details.DetailsFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.repo.RepoPageFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun details(user: User) = FragmentScreen { DetailsFragment.newInstance(user) }
    override fun convertor() = FragmentScreen { ConvertorFragment.newInstance() }
    override fun repoPage(repo: Repo) = FragmentScreen { RepoPageFragment.newInstance(repo) }
}