package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App

@Module
class ApplicationModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}