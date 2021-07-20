package ru.geekbrains.geekbrains_popular_libraries_kotlin.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GitHubService {

    @GET("/users")
    fun getUsersRequest(): Single<List<GitHubUser>>

    companion object {
        private const val BASE_URL = " https://api.github.com/"

        private val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        private val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service: GitHubService = retrofit.create(GitHubService::class.java)
    }
}