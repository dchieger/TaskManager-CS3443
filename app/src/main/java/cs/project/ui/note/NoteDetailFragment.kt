package cs.project.ui.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import cs.project.databinding.FragmentNoteDetailBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

private const val TAG = "NoteDetailFragment"


// WHY TF IS THIS BROKEN NAVIGATION SHOULD FUCKING WORK
class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: NoteDetailFragmentArgs by navArgs()

    private val noteDetailViewModel: NoteDetailViewModel by viewModels {
        NoteDetailViewModelFactory(args.noteId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val note = Note(
            id = UUID.randomUUID(),
            title = "",
            content = "",
            date = Date()
        )

        Log.d(TAG, "The note ID is: ${args.noteId}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            noteTitle.doOnTextChanged { text, _, _, _ ->
                noteDetailViewModel.updateNote { oldNote ->
                    oldNote.copy(title = text.toString())
                }
            }

            noteDate.apply {

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                noteDetailViewModel.note.collect { note ->
                    note?.let { updateUi(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(note: Note) {
        binding.apply {
            if (noteTitle.text.toString() != note.title) {
                noteTitle.setText(note.title)
            }
            noteDate.text = note.date.toString()
        }
    }

    companion object {
        fun showNoteDetail(noteId: UUID): Any {

        }
    }
}
