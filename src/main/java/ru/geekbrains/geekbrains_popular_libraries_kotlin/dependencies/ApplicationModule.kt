package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import javax.inject.Singleton

@Module
class ApplicationModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Singleton
    @Provides
    fun uiSchedulerProvider(): Scheduler = AndroidSchedulers.mainThread()
}