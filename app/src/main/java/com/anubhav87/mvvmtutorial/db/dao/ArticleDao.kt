package com.anubhav87.mvvmtutorial.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anubhav87.mvvmtutorial.db.entity.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table")
    fun getAllArticles() : List<Article>

    @Insert
    fun insertAll(articles: List<Article>?)


//    @Query("SELECT * FROM characters WHERE id = :id")
//    fun getCharacter(id: Int): LiveData<Character>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(character: Character)


}