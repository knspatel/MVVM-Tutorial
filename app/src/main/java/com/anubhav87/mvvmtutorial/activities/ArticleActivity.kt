package com.anubhav87.mvvmtutorial.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.adapter.MovieArticleAdapter
import com.anubhav87.mvvmtutorial.db.entity.Article
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.db.entity.Note
import com.anubhav87.mvvmtutorial.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_article.*
import org.koin.android.ext.android.inject

class ArticleActivity : AppCompatActivity() {

    private val articleViewModel: ArticleViewModel? by inject()
    //private val adapter: MovieArticleAdapter? by inject()

    private var layoutManager: LinearLayoutManager? = null
   // private var articleViewModel: ArticleViewModel? = null
    private var adapter: MovieArticleAdapter? = null
    private val articleArrayList: ArrayList<Article> = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        initialization()
        getMovieArticles()
    }

    private fun initialization() {

        progress_circular_movie_article.visibility = View.VISIBLE
        // use a linear layout manager
        layoutManager = LinearLayoutManager(ArticleActivity@ this)
        my_recycler_view.setLayoutManager(layoutManager)
        my_recycler_view.setHasFixedSize(true)

        // adapter
        adapter = MovieArticleAdapter(ArticleActivity@ this, articleArrayList)
        my_recycler_view.adapter = adapter

        // View Model
       //articleViewModel = ViewModelProviders.of(this)[ArticleViewModel::class.java]


    }

    private fun getMovieArticles() {

        articleViewModel!!.getArticles()!!.observe(this, Observer<ArticleResponse?>{
                articleResponse ->
                if (articleResponse != null) {
                    progress_circular_movie_article.visibility = View.GONE
                    val articles: List<Article?>? = articleResponse.articles
                    articleArrayList.addAll(articles as Collection<Article>)
                    adapter!!.notifyDataSetChanged()
                }
           }
        )


    }

}

//private fun <E> ArrayList<E>.addAll(elements: List<E?>?) {
//
//}

