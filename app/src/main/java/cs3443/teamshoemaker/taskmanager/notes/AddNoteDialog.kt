package cs3443.teamshoemaker.taskmanager.notes

import android.app.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.tiles.material.Text

@Composable
fun AddNoteDialog(
        state: NoteState,
        onEvent: (NoteEvent) -> Unit,
        modifier: Modifier = Modifier,
) {
//    AlertDialog(
//        modifier = modifier,
//        onDismissRequest = {
//            onEvent(NoteEvent.HideDialog)
//        },title = { Text(text = "Add note") },

}