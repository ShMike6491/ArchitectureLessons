package ru.geekbrains.geekbrains_popular_libraries_kotlin.model

class UsersStore {
    private val repositories = listOf(
        User("login1"),
        User("login2"),
        User("login3"),
        User("login4"),
        User("login5")
    )

    fun getUsers() : List<User> {
        return repositories
    }
}