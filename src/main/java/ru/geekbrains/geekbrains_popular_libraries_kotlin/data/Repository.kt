package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.GitHubService
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.asDomainRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.asDomainUsers

class Repository(private val service: GitHubService) : IRepository {

    override fun getUsers(): Single<List<User>> = service.getUsersRequest()
        .subscribeOn(Schedulers.io())
        .map { response -> response.asDomainUsers() }

    override fun getUserRepos(url: String): Single<List<Repo>> = service.getReposRequest(url)
        .subscribeOn(Schedulers.io())
        .map { response -> response.asDomainRepos() }
}