package ru.geekbrains.geekbrains_popular_libraries_kotlin.util

import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatActivity
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.CachedImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import java.io.File
import java.io.FileOutputStream

const val DEFAULT_IMAGE_PATH = ""

class ImageCacheImpl(private val db: LocalDatabase, val context: Context) : IImageCache {
    override fun getImageBytes(url: String): Single<ByteArray> = Single.fromCallable {
        val path = db.imageDao.getImageByUrl(url)?.localPath ?: DEFAULT_IMAGE_PATH
        val bitmap = BitmapFactory.decodeFile(path)
        bitmapToByteArray(bitmap)
    }.subscribeOn(Schedulers.io())

    override fun cacheImage(url: String, bytes: ByteArray?): Completable = Completable.fromAction {
        bytes?.let {
            val path = saveImageToFile(bytes)
            val image = CachedImage(url, path)
            db.imageDao.insert(image)
        }
    }.subscribeOn(Schedulers.io())

    private fun saveImageToFile(bytes: ByteArray): String {
        val wrapper = ContextWrapper(context)
        val file = wrapper.getDir("Img", MvpAppCompatActivity.MODE_PRIVATE)
        val fileName = File(file, "${System.currentTimeMillis()}.png")
        val fos = FileOutputStream(fileName)
        fos.write(bytes)
        fos.flush()
        fos.close()
        return fileName.absolutePath
    }
}