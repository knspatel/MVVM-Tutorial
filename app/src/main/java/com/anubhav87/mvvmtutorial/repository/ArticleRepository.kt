package com.anubhav87.mvvmtutorial.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.anubhav87.mvvmtutorial.db.dao.ArticleDao
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.retrofit.ApiRequest
import com.anubhav87.mvvmtutorial.retrofit.RetrofitRequest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ArticleRepository(private val articleDao: ArticleDao) {

    private val TAG = ArticleRepository::class.java.simpleName
    private var apiRequest: ApiRequest? = null
    val usersSuccessLiveData = MutableLiveData<ArticleResponse>()
    val usersFailureLiveData = MutableLiveData<Boolean>()


    fun initRetrofit() {
        apiRequest = RetrofitRequest.getRetrofitInstance()!!.create(ApiRequest::class.java)
    }

    suspend fun getMovieArticles(query: String?,key: String?) {

            val response = apiRequest!!.getMovieArticles(query, key)
            if (response != null) {
                Log.e(TAG, "SUCCESS")
                //Log.e(TAG, "${response!!.body()}")
                usersSuccessLiveData.postValue(response.body())
                var article = response.body()!!.articles
                Log.e(TAG, "getMovieArticles: "+article )

                val executor: Executor = Executors.newSingleThreadExecutor()
                executor.execute(Runnable {
                    if (article != null) {
                        articleDao.insertAll( article )
                    }
                })

            } else {
                Log.e(TAG, "FAILURE")
                Log.e(TAG, "${response!!.body()}")
                usersFailureLiveData.postValue(true)
            }

    }


//
//
//    fun getMovieArticles2(query: String?,key: String?): LiveData<ArticleResponse?>? {
//
//        val data =  MutableLiveData<ArticleResponse?>()
//
//        if (apiRequest != null) {
//            apiRequest!!.getMovieArticles(query, key)
//                ?.enqueue(object : Callback<ArticleResponse?> {
//                    override  fun onResponse(
//                        call: Call<ArticleResponse?>,
//                        response: Response<ArticleResponse?>
//                    ) {
//                        Log.e(TAG, "onResponse response:: $response")
//                        if (response.body() != null) {
//                            data.setValue(response.body())
//                            Log.e(TAG, "onResponse: " + response.body()!!.articles)
//
//
//                            var mList =  response.body()!!.articles
//                            if (mList != null) {
//                                GlobalScope.launch{
//                                    articleDao.insertAll(mList)
//                                }
//                            }
//                          //  articleDao.insertAll(response.body()!!.articles as List<Article>)
////                            InsertNoteAsyncTask(
////                                articleDao
////                            ).execute(mList)
//                        }
//                    }
//
//                    override fun onFailure(
//                        call: Call<ArticleResponse?>,
//                        t: Throwable
//                    ) {
//                        data.setValue(null)
//                    }
//                })
//        }
//        return data
//    }

//    private class InsertNoteAsyncTask(val articleDao: ArticleDao) : AsyncTask<List<Article>, Unit, Unit>() {
//        override fun doInBackground(vararg article: List<Article>) {
//            //articleDao.insertAll(article as List<Article>)
//        }
//    }


}