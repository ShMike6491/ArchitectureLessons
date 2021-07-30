package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LocalUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: LocalUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<LocalUser>)

    @Update
    fun update(user: LocalUser)

    @Update
    fun update(vararg users: LocalUser)

    @Update
    fun update(users: List<LocalUser>)

    @Delete
    fun delete(user: LocalUser)

    @Delete
    fun delete(vararg users: LocalUser)

    @Delete
    fun delete(users: List<LocalUser>)

    @Query("SELECT * FROM local_user")
    fun getAll(): List<LocalUser>

    @Query("SELECT * FROM local_user WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): LocalUser?
}

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LocalRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: LocalRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<LocalRepository>)

    @Update
    fun update(user: LocalRepository)

    @Update
    fun update(vararg users: LocalRepository)

    @Update
    fun update(users: List<LocalRepository>)

    @Delete
    fun delete(user: LocalRepository)

    @Delete
    fun delete(vararg users: LocalRepository)

    @Delete
    fun delete(users: List<LocalRepository>)

    @Query("SELECT * FROM local_repository")
    fun getAll(): List<LocalRepository>

    @Query("SELECT * FROM local_repository WHERE userId = :userId")
    fun findForUser(userId: String): List<LocalRepository>
}

