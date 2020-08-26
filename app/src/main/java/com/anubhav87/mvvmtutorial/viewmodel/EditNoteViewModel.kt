package com.anubhav87.mvvmtutorial.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anubhav87.mvvmtutorial.db.entity.Note
import com.anubhav87.mvvmtutorial.repository.EditNoteRepository


class EditNoteViewModel(private var repository: EditNoteRepository) : ViewModel() {

    //private var singleNote: LiveData<Note?>? = repository.getSingleNote()

    fun getSingleNote(noteId: Int): LiveData<Note?>? {
        return repository.getSingleNote(noteId)
    }
}