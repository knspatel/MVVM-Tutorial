package com.anubhav87.mvvmtutorial.db.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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