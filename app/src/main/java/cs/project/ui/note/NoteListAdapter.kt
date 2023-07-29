package cs.project.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.project.databinding.ListItemNoteBinding
import java.util.UUID

class NoteHolder(
    private val binding: ListItemNoteBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note, onNoteClicked: (NoteId: UUID) -> Unit) {
        binding.noteTitle.text = note.title
        binding.noteDate.text = note.date.toString()

        binding.root.setOnClickListener {
            onNoteClicked(note.id)
        }
    }
}

class NoteListAdapter(
    private val notes: List<Note>,
    private val onNoteClicked: (noteId: UUID) -> Unit
) : RecyclerView.Adapter<NoteHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val crime = notes[position]
        holder.bind(crime, onNoteClicked)
    }

    override fun getItemCount() = notes.size
}
