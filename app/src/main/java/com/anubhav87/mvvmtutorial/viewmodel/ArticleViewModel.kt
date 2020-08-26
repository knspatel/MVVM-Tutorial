package com.anubhav87.mvvmtutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.repository.ArticleRepository
import com.anubhav87.mvvmtutorial.utils.AppConstant.API_KEY
import com.anubhav87.mvvmtutorial.utils.AppConstant.ARTICLE_QUERY

class ArticleViewModel(private var repository: ArticleRepository) : ViewModel() {

    //private var articleRepository: ArticleRepository? = repository
   // private var articleResponseLiveData: LiveData<ArticleResponse?>? = repository!!.getMovieArticles(ARTICLE_QUERY, API_KEY)


//    fun ArticleViewModel(application: Application) {
//        //super(application)
//        articleRepository = ArticleRepository()
//        articleResponseLiveData = articleRepository!!.getMovieArticles(ARTICLE_QUERY, API_KEY)
//    }

    fun initRetrofit(){
        repository!!.initRetrofit()
    }
    fun getArticles(): LiveData<ArticleResponse?>? {
        initRetrofit()
        return repository!!.getMovieArticles(ARTICLE_QUERY, API_KEY)
    }

}