package com.anubhav87.mvvmtutorial.repository

//import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.anubhav87.mvvmtutorial.db.dao.NoteDao
import com.anubhav87.mvvmtutorial.db.entity.Note

class NoteRepository(private val noteDao: NoteDao) {

    private val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    fun update(note: Note) {

        //noteDao.updateNote()
        UpdateNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }


    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class UpdateNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        override fun doInBackground(vararg note: Note?) {
            note.get(0)?.id?.let { noteDao.updateNote(it, note.get(0)!!.title, note.get(0)!!.description) }
        }
    }
    private class InsertNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        override fun doInBackground(vararg note: Note?) {
            noteDao.insert(note[0]!!)
        }
    }


    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            noteDao.deleteAllNotes()
        }
    }

}