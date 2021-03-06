package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getUsers(): Single<List<User>>

    fun getUserRepos(url: String): Single<List<Repo>>
}