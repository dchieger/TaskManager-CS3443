package cs3443.teamshoemaker.taskmanager.notes

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //Need to write a better query for this but will sort the notes
    //in the order that they were created
    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getNotesByID(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesOrderedByTitle(): Flow<List<Note>>
}