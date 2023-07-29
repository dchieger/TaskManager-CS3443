package cs.project.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cs.project.databinding.FragmentNoteListBinding
import cs.project.ui.note.old.NoteFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "NoteListFragment"

class NoteListFragment : Fragment() {
    private  var _binding: FragmentNoteListBinding? = null
    //private var _binding: FragmentNoteList? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val noteListViewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)

        binding.noteRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                noteListViewModel.notes.collect { notes ->
                    binding.noteRecyclerView.adapter =
                        NoteListAdapter(notes) { noteId ->
                }
            }
        }
    } }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
