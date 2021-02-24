package com.anubhav87.mvvmtutorial.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.db.entity.Article
import com.anubhav87.mvvmtutorial.interfaces.DownloadMediaInterface
import com.bumptech.glide.Glide
import java.util.*

class MovieArticleAdapter(context : Context ,articleArrayList :  ArrayList<Article> , private val mDownloadListner : DownloadMediaInterface) : RecyclerView.Adapter<MovieArticleAdapter.ArticleViewHolder>() {

    private val context: Context? = context
    var articleArrayList: ArrayList<Article>? = articleArrayList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieArticleAdapter.ArticleViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_each_row_movie_article, parent, false)
        // mContext = parent.context ;
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articleArrayList!!.size
    }

    override fun onBindViewHolder(viewHolder: MovieArticleAdapter.ArticleViewHolder, position: Int) {
        val article = articleArrayList!![position]
        viewHolder.tvTitle.setText(article.title)
        viewHolder.tvAuthorAndPublishedAt.setText(
            "-" + article.author
                .toString() + " | " + "Published At: " + article.publishedAt
        )

        if(article.status == 0) {
            viewHolder.imgDownload.visibility = View.VISIBLE
            viewHolder.progressBar.visibility = View.GONE
            viewHolder.imgPlay.visibility = View.GONE
        }else if(article.status == 1){
            viewHolder.imgDownload.visibility = View.GONE
            viewHolder.progressBar.visibility = View.VISIBLE
            viewHolder.imgPlay.visibility = View.GONE
        }else {
            viewHolder.imgDownload.visibility = View.GONE
            viewHolder.progressBar.visibility = View.GONE
            viewHolder.imgPlay.visibility = View.VISIBLE

        }

        viewHolder.tvDescription.setText(article.description)
        Glide.with(context!!)
            .load(article.urlToImage)
            .into(viewHolder.imgViewCover)
        viewHolder.setListeners(article , position)

    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setListeners(
            article: Article,
            position: Int
        ) {
            imgDownload.setOnClickListener{
                Log.e("TAG", "setListeners: ${article.urlToImage}")
                Log.e("TAG", "setListeners: pos >>"+position)

                mDownloadListner.startDownload(
                    article.urlToImage,
                    "description ...",
                    position
                )
            }
        }


        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvAuthorAndPublishedAt: TextView = itemView.findViewById(R.id.tvAuthorAndPublishedAt)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var imgViewCover : ImageView = itemView.findViewById(R.id.imgViewCover)
        var imgDownload = itemView.findViewById<ImageView>(R.id.imgDownload)
        var progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        var imgPlay = itemView.findViewById<ImageView>(R.id.imgPlay)
    }
}