package cs3443.teamshoemaker.taskmanager.notes

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isAddingNote: Boolean = false
)
