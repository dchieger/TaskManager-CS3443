package notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Note(
    @PrimaryKey val id: UUID,
    val title: String,
    val content: String,
    val date: Date,
    )