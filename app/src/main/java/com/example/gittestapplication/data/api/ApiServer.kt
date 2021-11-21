package com.example.gittestapplication.data.api

import com.example.gittestapplication.data.model.SearchRepositoryResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiServer {
    private const val API_BASE_URL = "https://api.github.com/"

    private var client: OkHttpClient? = null

    private fun retrofit() = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(getOrCreateOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getOrCreateOkHttpClient(): OkHttpClient {
        return client ?: OkHttpClient.Builder().build().also { client = it }
    }

    fun apiService() = retrofit().create(GitService::class.java)

    interface GitService {
        @GET("search/repositories")
        suspend fun searchRepositoriesResult(
            @Query("q") query: String,
            @Query("sort") sort: String,
            @Query("order") order: String,
            @Query("per_page") pageSize: Int,
            @Query("page") pageNumber: Int,
        ): SearchRepositoryResponse
    }
}