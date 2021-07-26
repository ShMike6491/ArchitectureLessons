package ru.geekbrains.geekbrains_popular_libraries_kotlin

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.IRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.GitHubService

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //temp repo
    fun repository(): IRepository = Repository(GitHubService.service)

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