package com.anubhav87.mvvmtutorial.adapter


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.anubhav87.mvvmtutorial.R
import com.anubhav87.mvvmtutorial.activities.AddNoteActivity
import com.anubhav87.mvvmtutorial.activities.EditNoteActivity
import com.anubhav87.mvvmtutorial.activities.MainActivity
import com.anubhav87.mvvmtutorial.activities.MainActivity.Companion.UPDATE_NOTE_ACTIVITY_REQUEST_CODE
import com.anubhav87.mvvmtutorial.db.entity.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Note> = ArrayList()
   // lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
       // mContext = parent.context ;
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.setListeners(currentNote)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setListeners(currentNote: Note) {
            var intent = Intent(itemView.context, EditNoteActivity::class.java)

            ivRowDelete.setOnClickListener{
                Toast.makeText(itemView.context, "clicked id >> " +currentNote.id, Toast.LENGTH_SHORT).show()
            }
            ivRowEdit.setOnClickListener{
                intent.putExtra("note_id", currentNote.id)
                (itemView.context as Activity).startActivityForResult(intent, UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
        }

        var textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        var textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
        var ivRowDelete : ImageView = itemView.findViewById(R.id.ivRowDelete)
        var ivRowEdit : ImageView = itemView.findViewById(R.id.ivRowEdit)

    }
}