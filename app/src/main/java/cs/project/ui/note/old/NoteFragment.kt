package cs.project.ui.note.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cs.project.databinding.FragmentNoteBinding


// I BELIVE THIS IS WHAT WILL TURN INTO NOTELSIT


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val noteViewModel =
            ViewModelProvider(this).get(NoteViewModel::class.java)

        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        noteViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}