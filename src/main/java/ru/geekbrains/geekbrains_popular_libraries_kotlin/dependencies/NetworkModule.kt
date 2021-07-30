package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote.GitHubService
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.AndroidNetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.INetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): GitHubService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(GitHubService::class.java)


    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()


    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}