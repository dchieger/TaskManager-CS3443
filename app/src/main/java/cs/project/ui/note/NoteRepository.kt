package cs.project.ui.note

import android.content.Context
import androidx.room.Room
import cs.project.ui.note.database.*
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

private const val DATABASE_NAME = "note-database"

class NoteRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val database: NoteDatabase = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()

    fun getNotes(): Flow<List<Note>> = database.NoteDAO().getNotes()

    suspend fun  getNote(id: UUID): Note = database.NoteDAO().getNote(id)

    fun updateNotes(note: Note){
        coroutineScope.launch {
            database.NoteDAO().updateNote(note)
        }
    }

    companion object {
        private var INSTANCE: NoteRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository(context)
            }
        }

                fun get(): NoteRepository {
            return INSTANCE
                ?: throw IllegalStateException("Damn, Notes Repo must be initialized")
        }
    }
}