package calendar
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Event(
    @PrimaryKey val id: UUID,
    val title: String,
    val content: String,
    val date: Date,
    val complete: Boolean,
    )