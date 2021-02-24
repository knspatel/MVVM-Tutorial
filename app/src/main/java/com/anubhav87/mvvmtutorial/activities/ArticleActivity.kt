package com.anubhav87.mvvmtutorial.activities

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.adapter.MovieArticleAdapter
import com.anubhav87.mvvmtutorial.db.NoteDatabase
import com.anubhav87.mvvmtutorial.db.entity.Article
import com.anubhav87.mvvmtutorial.interfaces.DownloadMediaInterface
import com.anubhav87.mvvmtutorial.viewmodel.ArticleViewModel
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.activity_article.*
import org.koin.android.ext.android.inject


class ArticleActivity : AppCompatActivity() {

    private val articleViewModel: ArticleViewModel? by inject()

    private var layoutManager: LinearLayoutManager? = null
    private var adapter: MovieArticleAdapter? = null
    private val articleArrayList: ArrayList<Article> = ArrayList<Article>()
    var db: NoteDatabase? = null
    var downloadManager: DownloadManager? = null
    var pos = 0
    var downloadID : Long = 0

    private val downloadCompleteReceiver: BroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                val id = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (downloadID == id) {
                    Log.e("TAG", "onReceive: >>>  download completed " +pos)
                    articleArrayList.get(pos).status = 2
                    adapter!!.notifyItemChanged(pos)
                }

            }
        }
    }


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

        adapter = MovieArticleAdapter(
            ArticleActivity@ this,
            articleArrayList,
            object : DownloadMediaInterface {
                override fun startDownload(
                    downloadPath: String,
                    description: String,
                    position: Int
                ) {
                    pos = position
                    articleArrayList.get(pos).status = 1
                    adapter!!.notifyItemChanged(pos)

                    startDownload2(downloadPath, description)
                }
            })
        my_recycler_view.adapter = adapter

        //init download manager
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        registerReceiver(
            downloadCompleteReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadCompleteReceiver)
    }

    private fun getMovieArticles() {
        articleViewModel!!.getArticles()
    }

    private fun registerObservers() {
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

    fun startDownload2(downloadPath: String, description: String) {
        var downloadPath2 = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
        Permissions.check(
            ArticleActivity@ this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null,
            object : PermissionHandler() {
                override fun onGranted() {
                    try {
                       // Log.e("TAG", "downloadPath: " +downloadPath)
                        //Log.e("TAG", "destinationUri: " +mydir.name)
                       downloadManager?.apply {
                          downloadID =  enqueue(
                                DownloadManager.Request(Uri.parse(downloadPath2))
                                    .setAllowedNetworkTypes(
                                        DownloadManager.Request.NETWORK_WIFI or
                                                DownloadManager.Request.NETWORK_MOBILE
                                    )
                                    .setAllowedOverRoaming(false)
                                    .setTitle(getString(R.string.app_name))
                                    .setDescription(description)
                                    //.setDestinationUri(mydir.absolutePath)
                                    .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                                    .setAllowedOverRoaming(true)// Set if download is allowed on roaming network
                                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS ,"/MVVM/"  + "/" +downloadPath2.substring(downloadPath2.lastIndexOf('/') + 1))
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            )
                           Log.e("TAG", "downloadID : "+downloadID )
                        }
                    } catch (ex: Exception) {
                        Log.e("TAG", "Error downloading data.${ex.message}")
                    }

                }
            });
    }

}




