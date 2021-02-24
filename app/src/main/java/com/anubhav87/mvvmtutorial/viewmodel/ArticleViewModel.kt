package com.anubhav87.mvvmtutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.repository.ArticleRepository
import com.anubhav87.mvvmtutorial.utils.AppConstant.API_KEY
import com.anubhav87.mvvmtutorial.utils.AppConstant.ARTICLE_QUERY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleViewModel(private var repository: ArticleRepository) : ViewModel() {

    val usersSuccessLiveData = repository.usersSuccessLiveData
    val usersFailureLiveData = repository.usersFailureLiveData


    fun initRetrofit() {
        repository!!.initRetrofit()
    }

    fun getArticles()  {
        initRetrofit()
        viewModelScope.launch {
             repository!!.getMovieArticles(ARTICLE_QUERY, API_KEY)

        }
    }

}