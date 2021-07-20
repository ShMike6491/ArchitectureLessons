package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersStore {
    private val repositories = listOf(
        User("login1"),
        User("login2"),
        User("login3"),
        User("login4"),
        User("login5")
    )

    fun getObservable() : Observable<List<User>> = Observable
        .just(repositories)
        .subscribeOn(Schedulers.io())
}