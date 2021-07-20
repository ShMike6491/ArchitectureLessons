package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String? = null,
    val login: String? = null,
    val avatar: String? = null,
    val repos: String? = null
) : Parcelable

@Parcelize
data class Repo (
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val publishedAt: String? = null,
    val language: String? = null,
    val forks: Int? = null,
    val stars: Int? = null,
    val watchers: Int? = null,
    val issues: Int? = null,
) : Parcelable