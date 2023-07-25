package cs3443.teamshoemaker.taskmanager.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoteViewModel (private val dao:NoteDAO): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.BY_TITLE)
    private val _notes = _sortType
        .flatMapLatest { sortType ->
            when(sortType){
                SortType.BY_TITLE -> dao.getNotesOrderedByTitle()
                SortType.BY_DATE -> dao.getNotesByID()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            NoteEvent.ShowDialog -> _state.update { it.copy(
                isAddingNote = true
            ) }
            NoteEvent.HideDialog -> _state.update { it.copy(
                isAddingNote = false
            ) }
            NoteEvent.saveNote -> {
                val title = _state.value.title
                val description = _state.value.description

                if(title.isBlank() || description.isBlank()){
                    return
                }

                val note = Note(
                    title = title,
                    description = description
                )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }

                _state.update { it.copy(
                    isAddingNote = false,
                    title = "",
                    description = "",
                ) }
            }
            is NoteEvent.setDescription -> {
                _state.update { it.copy(
                    description = event.Description
                ) }
            }
            is NoteEvent.setTitle -> {
                _state.update { it.copy(
                    title = event.Title
                ) }
            }

            is NoteEvent.deleteNote -> viewModelScope.launch {
                dao.deleteNote(event.note)
            }
        }
    }
}