package ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

const val FREEZE_TAG = "Error On Freeze"
const val PATH_TAG = "Load File Path"

class ImageConvertor(private val context: Context) : IConvertor {

    override fun convert(imageResource: IImage): Completable = Completable.fromAction {
        freezeThread(2000)
        val bitmap = convertUriToBitmap(imageResource.getPath())
        saveImageToFile(bitmap)
    }.subscribeOn(Schedulers.io())

    private fun convertUriToBitmap(imageUri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    imageUri
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }
    }

    private fun saveImageToFile(bitmap: Bitmap) {
        val imageFileName = "GeekBrains-${System.currentTimeMillis()}.png"
        val root = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Environment.getExternalStorageDirectory().toString()
        } else {
            context.getExternalFilesDir(null)?.absolutePath
        }

        val dir = File(root!!)
        val file = File(dir, imageFileName)

        if (file.exists()) file.delete()

        Log.i(PATH_TAG, root + imageFileName)

        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.flush()
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun freezeThread(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            Log.e(FREEZE_TAG, e.message, e)
        }
    }
}