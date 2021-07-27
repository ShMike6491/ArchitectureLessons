package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.asDomainRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.asDomainUsers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.INetworkStatus

class Repository(
    private val service: GitHubService,
    private val networkStatus: INetworkStatus,
    private val db: LocalDatabase
) : IRepository {

    override fun getUsers(): Single<List<User>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                service.getUsersRequest()
                    .flatMap { users ->
                        Single.fromCallable {
                            db.userDao.insert(users.asDatabaseUsers())
                            users.asDomainUsers()
                        }
                    }
            } else {
                Single.fromCallable { db.userDao.getAll().asDomainUsers() }
            }
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(user: User): Single<List<Repo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.repos?.let { url ->
                    service.getReposRequest(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.asDatabaseRepos(roomUser.id)
                                db.repositoryDao.insert(roomRepos)
                                repositories.asDomainRepos()
                            }
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).asDomainRepo()
                }
            }
        }.subscribeOn(Schedulers.io())
}