package ru.geekbrains.geekbrains_popular_libraries_kotlin

import android.app.Application
import ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies.ApplicationComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies.ApplicationModule
import ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies.DaggerApplicationComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: ApplicationComponent

//    val database by lazy { LocalDatabase.create(this) }
//
//    //temp repo
//    val repository by lazy { Repository(GitHubService.service, AndroidNetworkStatus(this), database) }
//
//    //temp
//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }
//    val navigatorHolder get() = cicerone.getNavigatorHolder()
//    val router get() = cicerone.router
//    //temp

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}