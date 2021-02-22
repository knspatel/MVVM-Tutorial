package com.anubhav87.mvvmtutorial.retrofit

import com.anubhav87.mvvmtutorial.db.entity.Article
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {


    @GET("v2/everything/")
    suspend fun getMovieArticles(
        @Query("q") query: String?,
        @Query("apikey") apiKey: String?
    ): Response<ArticleResponse?>?
}