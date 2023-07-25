package cs3443.teamshoemaker.taskmanager.notes

sealed interface NoteEvent {
    object saveNote: NoteEvent
    data class deleteNote(val note: Note): NoteEvent

    data class setTitle(val Title: String): NoteEvent
    data class setDescription(val Description: String): NoteEvent

    object ShowDialog : NoteEvent
    object HideDialog : NoteEvent
}