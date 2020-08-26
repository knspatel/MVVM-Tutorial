package com.anubhav87.mvvmtutorial.db.entity

import com.google.gson.annotations.Expose

data class Article(
    @Expose
    var title: String = "",
// private String title;
//private String author;
//private String urlToImage;
//private String publishedAt;
//private String description;


    @Expose
    var author: String = "",
    @Expose
    var urlToImage: String = "",
    @Expose
    var publishedAt: String = "",
    @Expose
    var description: String = ""
)
