package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GitHubService {

    @GET("/users")
    fun getUsersRequest(): Single<List<GitHubUser>>

    @GET
    fun getReposRequest(@Url url: String): Single<List<GitHubRepo>>
}