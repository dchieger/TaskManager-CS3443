package cs.project.ui.note.database

import androidx.room.TypeConverter
import java.util.*

class NoteTypeConvertors {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}