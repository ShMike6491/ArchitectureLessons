package ru.geekbrains.geekbrains_popular_libraries_kotlin.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String? = null,
    val login: String? = null,
    val avatar: String? = null
) : Parcelable