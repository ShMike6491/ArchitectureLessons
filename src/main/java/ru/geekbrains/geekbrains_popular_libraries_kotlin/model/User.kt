package ru.geekbrains.geekbrains_popular_libraries_kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String
) : Parcelable