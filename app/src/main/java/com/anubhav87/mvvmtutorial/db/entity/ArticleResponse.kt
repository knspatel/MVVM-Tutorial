package com.anubhav87.mvvmtutorial.db.entity

import androidx.lifecycle.LiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @SerializedName("status")
    @Expose
    public val status: String? = null,

    @SerializedName("totalResults")
    @Expose
    public val totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    public var articles: List<Article>? = null
)