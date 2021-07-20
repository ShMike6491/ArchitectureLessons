package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote

import com.google.gson.annotations.Expose
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User

data class GitHubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null,
    @Expose val followersUrl: String? = null,
    @Expose val followingUrl: String? = null,
    @Expose val gistsUrl: String? = null,
    @Expose val starredUrl: String? = null,
    @Expose val subscriptionsUrl: String? = null,
    @Expose val organizationsUrl: String? = null,
    @Expose val eventsUrl: String? = null
)

/**
 * Extension function to convert network data layer to the application layer
 */
fun List<GitHubUser>.asDomainUsers(): List<User> = map {
    User(
        id = it.id,
        login = it.login,
        avatar = it.avatarUrl
    )
}