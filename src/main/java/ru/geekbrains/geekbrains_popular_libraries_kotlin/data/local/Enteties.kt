package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User

@Entity(tableName = "local_user")
data class LocalUser(
    @PrimaryKey val id: String,
    val login: String,
    val avatar: String,
    val repos: String
)

@Entity(
    tableName = "local_repository",
    foreignKeys = [ForeignKey(
        entity = LocalUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class LocalRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    val starsCount: Int,
    val language: String,
    var userId: String
)

/**
 * Extension function to convert database entities to the application layer
 */
fun List<LocalUser>.asDomainUsers(): List<User> = map {
    User(id = it.id, login = it.login, avatar = it.avatar, repos = it.repos)
}

/**
 * Extension function to convert database entities to the application layer
 */
fun List<LocalRepository>.asDomainRepo(): List<Repo> = map {
    Repo(id = it.id, name = it.name, forks = it.forksCount, stars = it.starsCount, language = it.language)
}