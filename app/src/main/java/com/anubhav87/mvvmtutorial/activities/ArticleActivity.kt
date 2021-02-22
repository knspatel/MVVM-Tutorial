package com.anubhav87.mvvmtutorial.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.adapter.MovieArticleAdapter
import com.anubhav87.mvvmtutorial.db.NoteDatabase
import com.anubhav87.mvvmtutorial.db.NoteDatabase.Companion.getInstance
import com.anubhav87.mvvmtutorial.db.entity.Article
import com.anubhav87.mvvmtutorial.db.entity.ArticleResponse
import com.anubhav87.mvvmtutorial.db.entity.Note
import com.anubhav87.mvvmtutorial.utils.AppUtils
import com.anubhav87.mvvmtutorial.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_article.*
import org.koin.android.ext.android.inject
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ArticleActivity : AppCompatActivity() {

    private val articleViewModel: ArticleViewModel? by inject()
    //private val adapter: MovieArticleAdapter? by inject()

    private var layoutManager: LinearLayoutManager? = null
   // private var articleViewModel: ArticleViewModel? = null
    private var adapter: MovieArticleAdapter? = null
    private val articleArrayList: ArrayList<Article> = ArrayList<Article>()
    var db: NoteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        initialization()

        //before calling api register live data observer
        registerObservers()
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
    //   articleViewModel = ViewModelProviders.of(this)[ArticleViewModel::class.java]

    }

    private fun getMovieArticles() {
       // if(AppUtils.hasNetwork(ArticleActivity@this)){
            articleViewModel!!.getArticles()
//        }
//        else{
//             data from db
//            val executor: Executor = Executors.newSingleThreadExecutor()
//            executor.execute(Runnable {
//                progress_circular_movie_article.visibility = View.GONE
//
//                var mList =  getInstance(ArticleActivity@this).articleDao().getAllArticles()
//                Log.e("TAG", "getMovieArticles::: "+mList.toString())
//
//
//            })
//        }
//
//        articleViewModel!!.getArticles()!!.observe(this, Observer<ArticleResponse?>{
//                articleResponse ->
//                if (articleResponse != null) {
//                    progress_circular_movie_article.visibility = View.GONE
//                    val articles: List<Article?>? = articleResponse.articles
//                    articleArrayList.addAll(articles as Collection<Article>)
//                    adapter!!.notifyDataSetChanged()
//                }
//           }
//        )
    }

    private fun registerObservers(){
        articleViewModel!!.usersSuccessLiveData.observe(this, Observer { articleList ->
            //if it is not null then we will display all users
            articleList?.let {
                //userAdapter.setUsers(it)
                  progress_circular_movie_article.visibility = View.GONE
                    val articles: List<Article>? = it.articles
                    articleArrayList.addAll(articles as Collection<Article>)
                    adapter!!.notifyDataSetChanged()
            }
        })

        articleViewModel!!.usersFailureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                Toast.makeText(this, "Oops! something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }
}




