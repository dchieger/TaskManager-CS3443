package cs.project.ui.note.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cs.project.databinding.FragmentNotesBinding


// I BELIVE THIS IS WHAT WILL TURN INTO NOTELSIT


class NoteFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null

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

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        noteViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}