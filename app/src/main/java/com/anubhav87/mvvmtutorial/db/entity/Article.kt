package com.anubhav87.mvvmtutorial.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "article_table")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,

    @Expose
    var title: String = "",

    @Expose
    var author: String = "",
    @Expose
    var urlToImage: String = "",
    @Expose
    var publishedAt: String = "",
    @Expose
    var description: String = "",


    var status : Int = 0

)
