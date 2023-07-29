package cs.project.ui.note.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import cs.project.ui.note.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id=(:id)")
    suspend fun getNote(id: UUID): Note

    @Update
    suspend fun updateNote(note: Note)
}