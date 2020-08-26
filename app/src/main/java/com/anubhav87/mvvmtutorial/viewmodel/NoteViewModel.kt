package com.anubhav87.mvvmtutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anubhav87.mvvmtutorial.db.entity.Note
import com.anubhav87.mvvmtutorial.repository.NoteRepository


class NoteViewModel(private var repository: NoteRepository) : ViewModel() {

    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    fun updateNotes(note: Note) {
        repository.update(note)
    }
}