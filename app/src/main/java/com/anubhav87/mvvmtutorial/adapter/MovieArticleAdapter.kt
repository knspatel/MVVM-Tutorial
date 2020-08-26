package com.anubhav87.mvvmtutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.db.entity.Article
import com.bumptech.glide.Glide
import java.util.*

class MovieArticleAdapter(context : Context ,articleArrayList :  ArrayList<Article> ) : RecyclerView.Adapter<MovieArticleAdapter.ArticleViewHolder>() {

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
        viewHolder.tvDescription.setText(article.description)
        Glide.with(context!!)
            .load(article.urlToImage)
            .into(viewHolder.imgViewCover)

    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvAuthorAndPublishedAt: TextView = itemView.findViewById(R.id.tvAuthorAndPublishedAt)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var imgViewCover : ImageView = itemView.findViewById(R.id.imgViewCover)
    }
}