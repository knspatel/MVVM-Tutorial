package com.anubhav87.mvvmtutorial.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.support.v7.widget.DialogTitle
import com.anubhav87.mvvmtutorial.db.entity.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes_table ")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id=:noteId")
    fun getNote(noteId: Int): LiveData<Note?>?

    @Query("UPDATE notes_table  SET title =:mTitle , description =:mDescription WHERE id=:noteId")
    fun updateNote(noteId : Int , mTitle : String , mDescription : String)

}