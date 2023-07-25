package cs3443.teamshoemaker.taskmanager.notes

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.floatingactionbutton.FloatingActionButton


@Composable
fun NoteScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(NoteEvent.showDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note"
                )
            }
        },
    ) { _ ->
        if(state.isAddingNote) {
            AddNoteDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
            }
            items(state.notes) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.fillMaxWidth(),
                    onEditClick = {
                        onEvent(NoteEvent.EditNoteClicked(note))
                    },
                    onDeleteClick = {
                        onEvent(NoteEvent.DeleteNoteClicked(note))
                    }
                )
            }
        }
    }
    )

}
     }