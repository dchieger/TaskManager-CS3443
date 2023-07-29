package cs.project.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class NoteDetailViewModel(noteId: UUID) : ViewModel() {
    private val noteRepository = NoteRepository.get()

    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note: StateFlow<Note?> = _note.asStateFlow()

    init {
        viewModelScope.launch {
            _note.value = noteRepository.getNote(noteId)
        }
    }

    fun updateNote(onUpdate: (Note) -> Note) {
        _note.update { oldNote ->
            oldNote?.let { onUpdate(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        note.value?.let { noteRepository.updateNotes(it) }
    }
}

class NoteDetailViewModelFactory(
    private val noteId: UUID
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteDetailViewModel(noteId) as T
    }
}
