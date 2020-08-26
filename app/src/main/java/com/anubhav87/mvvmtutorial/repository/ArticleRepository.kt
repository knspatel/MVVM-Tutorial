package com.anubhav87.mvvmtutorial.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.retrofit.ApiRequest
import com.anubhav87.mvvmtutorial.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository() {

    private val TAG = ArticleRepository::class.java.simpleName
    private var apiRequest: ApiRequest? = null

    fun initRetrofit() {
        apiRequest = RetrofitRequest.getRetrofitInstance()!!.create(ApiRequest::class.java)
    }
//
//    fun getMovieArticles(query : String , key : String): Call<ArticleResponse?>? {
//        if (!AppUtils.hasNetwork(context)) {
//            throw UnknownHostException("No internet connection found.")
//        }
//        return articleService.getMovieArticles(query , key)
//    }


    fun getMovieArticles(query: String?,key: String?): LiveData<ArticleResponse?>? {

        val data =  MutableLiveData<ArticleResponse?>()

        if (apiRequest != null) {
            apiRequest!!.getMovieArticles(query, key)
                ?.enqueue(object : Callback<ArticleResponse?> {
                    override fun onResponse(
                        call: Call<ArticleResponse?>,
                        response: Response<ArticleResponse?>
                    ) {
                        Log.e(TAG, "onResponse response:: $response")
                        if (response.body() != null) {
                            data.setValue(response.body())
//                            Log.d(TAG,"articles total result:: " + response.body().TotalResults())
//                            Log.d(TAG,"articles size:: " + response.body().getArticles().size())
//                            Log.d(TAG,"articles title pos 0:: " + response.body().getArticles().get(0).getTitle())
                        }
                    }

                    override fun onFailure(
                        call: Call<ArticleResponse?>,
                        t: Throwable
                    ) {
                        data.setValue(null)
                    }
                })
        }
        return data
    }

}