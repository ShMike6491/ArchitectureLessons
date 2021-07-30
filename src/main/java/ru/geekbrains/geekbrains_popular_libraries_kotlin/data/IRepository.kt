package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getUsers(): Single<List<User>>

    fun getUserRepos(user: User): Single<List<Repo>>
}