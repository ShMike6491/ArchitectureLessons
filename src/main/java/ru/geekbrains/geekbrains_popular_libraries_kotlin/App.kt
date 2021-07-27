package ru.geekbrains.geekbrains_popular_libraries_kotlin

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.GitHubService
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.AndroidNetworkStatus

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val database by lazy { LocalDatabase.create(this) }

    //temp repo
    val repository by lazy { Repository(GitHubService.service, AndroidNetworkStatus(this), database) }

    //temp
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    //temp

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}