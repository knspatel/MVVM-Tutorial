package com.anubhav87.mvvmtutorial.repository

//import android.arch.lifecycle.LiveData
import androidx.lifecycle.LiveData
import com.anubhav87.mvvmtutorial.db.dao.NoteDao
import com.anubhav87.mvvmtutorial.db.entity.Note

class EditNoteRepository(private val noteDao: NoteDao) {

    //private val singleNote: LiveData<Note?>? =

    fun getSingleNote(noteId: Int): LiveData<Note?>? {
        return noteDao.getNote(noteId)
    }
}