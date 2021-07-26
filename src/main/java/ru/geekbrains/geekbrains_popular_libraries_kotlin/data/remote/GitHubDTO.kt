package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote

import com.google.gson.annotations.Expose
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
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

data class GitHubRepo(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val owner: GitHubUser? = null,
    @Expose val description: String? = null,
    @Expose val createdAt: String? = null,
    @Expose val updatedAt: String? = null,
    @Expose val publishedAt: String? = null,
    @Expose val language: String? = null,
    @Expose val forksCount: Int? = null,
    @Expose val stargazersCount: Int? = null,
    @Expose val watchersCount: Int? = null,
    @Expose val openIssuesCount: Int? = null,
)

/**
 * Extension function to convert network data layer to the application layer
 */
fun List<GitHubUser>.asDomainUsers(): List<User> = map {
    User(
        id = it.id,
        login = it.login,
        avatar = it.avatarUrl,
        repos = it.reposUrl
    )
}

/**
 * Extension function to convert network data layer to the application layer
 */
fun List<GitHubRepo>.asDomainRepos(): List<Repo> = map {
    Repo(
        id = it.id,
        name = it.name,
        description = it.description,
        createdAt = it.createdAt,
        updatedAt = it.updatedAt,
        publishedAt = it.publishedAt,
        language = it.language,
        forks = it.forksCount,
        stars = it.stargazersCount,
        watchers = it.watchersCount,
        issues = it.openIssuesCount
    )
}