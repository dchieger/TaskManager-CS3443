package cs.project.ui.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import java.util.Date

data class Note (
    @PrimaryKey val id:UUID,
    val title: String,
    val content: String,
    val date: Date

)