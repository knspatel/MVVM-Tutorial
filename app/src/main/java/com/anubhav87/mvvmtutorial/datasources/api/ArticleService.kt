package com.myabilities.pitchai.data.datasources.api

import com.anubhav87.mvvmtutorial.db.entity.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleService {

//    @POST("pitches/savePitcher")
//    suspend fun savePitcher(@Body pitcher: Pitcher): DataResponse<Long>
//
//    @GET("pitches/GetPitcherById")
//    suspend fun getPitcher(@Query("pitcherId") pitcherId: Long): DataResponse<Pitcher>

    @GET("v2/everything/")
    fun getMovieArticles(
        @Query("q") query: String?,
        @Query("apikey") apiKey: String?
    ): Call<Article?>?
}