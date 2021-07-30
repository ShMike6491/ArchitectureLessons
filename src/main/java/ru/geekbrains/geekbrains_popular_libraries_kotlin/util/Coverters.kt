package ru.geekbrains.geekbrains_popular_libraries_kotlin.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun bitmapToByteArray(bitmap: Bitmap?): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}