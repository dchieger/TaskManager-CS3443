package cs3443.teamshoemaker.taskmanager.notes

import android.app.AlertDialog
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tiles.material.Text


@Composable
fun AddNoteDialog(
        state: NoteState,
        onEvent: (NoteEvent) -> Unit,
        modifier: Modifier = Modifier,
) { AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(NoteEvent.hideDialog)
        },
        title = { Text(text = "Add Note") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(NoteEvent.setTitle(it))
                    },
                    placeholder = {
                        Text(text = "Title")
                    }
                )
                TextField(
                    value = state.description,
                    onValueChange = {
                        onEvent(NoteEvent.setDescription(it))
                    },
                    placeholder = {
                        Text(text = "Descritpion")
                    }
                )
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(NoteEvent.saveNote)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )

}