package ru.geekbrains.geekbrains_popular_libraries_kotlin.util

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.INetworkStatus

class GlideImageLoader(
    private val networkStatus: INetworkStatus,
    private val imageCache: IImageCache,
    private val mainThread: Scheduler
) : IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                Glide.with(container.context)
                    .asBitmap()
                    .load(url)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ) = false

                        override fun onResourceReady(
                            bitmap: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val image = bitmapToByteArray(bitmap)
                            imageCache.cacheImage(url, image)
                                .observeOn(Schedulers.io())
                                .subscribe()
                            return false
                        }
                    })
                    .into(container)
            } else {
                imageCache.getImageBytes(url).observeOn(mainThread).subscribe { bytes ->
                    Glide.with(container.context)
                        .asBitmap()
                        .load(bytes)
                        .into(container)
                }
            }
        }
    }
}