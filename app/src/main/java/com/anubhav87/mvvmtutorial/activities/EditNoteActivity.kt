package com.anubhav87.mvvmtutorial.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.db.entity.Note
import com.anubhav87.mvvmtutorial.viewmodel.EditNoteViewModel
import com.anubhav87.mvvmtutorial.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit.*
import org.koin.android.ext.android.inject

class EditNoteActivity : AppCompatActivity() , View.OnClickListener{
    val TAG = this.javaClass.simpleName

    val NOTE_ID = "note_id"
    val UPDATED_NOTE = "note_text"
    private var bundle: Bundle? = null
    private var noteId: Int? = null
    private val note: LiveData<Note>? = null


    //var noteModel: EditNoteViewModel? = null
    private val editNoteViewModel: EditNoteViewModel by inject()
    private val noteViewModel : NoteViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setListner()

        //get data form intent
        bundle = this.intent.extras
        if (bundle != null) {
            noteId = bundle!!.getInt("note_id") // note id of adapter's click position
        }

        Log.e(TAG, "noteId >>> " + noteId)
        Toast.makeText(this@EditNoteActivity, "clicked noteId >> " + noteId, Toast.LENGTH_SHORT)
            .show()

        noteId?.let { noteId ->
            editNoteViewModel.getSingleNote(noteId)?.observe(this, Observer { note ->
                note?.let {
                    etNoteTitle?.setText(it.title)
                    etNoteDescription?.setText(it.description)
                    }
            })
        }
    }

    private fun setListner() {
        btn_update.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_update ->{
                val note = Note(etNoteTitle.text.toString() , etNoteDescription.text.toString())
                note.id = this!!.noteId!!;

                noteId?.let { noteViewModel.updateNotes(note) }

                Toast.makeText(this, "Note updated!", Toast.LENGTH_SHORT).show()

            }
            R.id.btn_cancel ->{

            }
        }
    }
}