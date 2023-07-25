package cs3443.teamshoemaker.taskmanager.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)