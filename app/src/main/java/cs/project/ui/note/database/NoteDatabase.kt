package cs.project.ui.note.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cs.project.ui.note.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(NoteTypeConvertors::class)

abstract class NoteDatabase {
    abstract fun NoteDAO(): NoteDAO
}