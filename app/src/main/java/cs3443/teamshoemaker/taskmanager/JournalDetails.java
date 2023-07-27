package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import android.content.Intent;

import android.view.View;

import java.util.Date;
import java.io.IOException;



public class JournalDetails extends AppCompatActivity {


    private EditText titleEditText, contentEditText;
    private ImageButton saveJournalBtn;
    private ImageButton deleteJournalBtn;
    private JournalEntry selectedJournal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);
        initWidgets();
        checkForEditJournal();
        saveJournalBtn = findViewById(R.id.save_journal_btn);
        saveJournalBtn.setOnClickListener((v) -> {
            try {
                saveJournal();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteJournalBtn.setOnClickListener((v) -> {
            deleteJournal();

        });


    }


    private void initWidgets() {
        titleEditText = findViewById(R.id.journal_title_text);
        contentEditText = findViewById(R.id.journal_content_text);
        deleteJournalBtn = findViewById(R.id.delete_journal_btn);
        saveJournalBtn = findViewById(R.id.save_journal_btn);

    }

    private void checkForEditJournal() {
        Intent previousIntent = getIntent();

        int passedJournalID = previousIntent.getIntExtra(JournalEntry.JOURNAL_EDIT_EXTRA, -1);
        selectedJournal = JournalEntry.getJournalForID(passedJournalID);

        if (selectedJournal != null) {
            titleEditText.setText(selectedJournal.getTitle());
            contentEditText.setText(selectedJournal.getContent());
        } else {
            deleteJournalBtn.setVisibility(View.INVISIBLE);
        }
    }


    //if the title is empty
    public void saveJournal() throws IOException {
        JournalEntryDatabaseHelper sqLiteManager = JournalEntryDatabaseHelper.instanceOfDatabase(this);
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (title == null || title.isEmpty()) {
            titleEditText.setError("Title is required");
            return;
        }
        if (selectedJournal == null) {
            int id = JournalEntry.journalArrayList.size();
            JournalEntry newJournal = new JournalEntry(id, title, content);
            JournalEntry.journalArrayList.add(newJournal);
            sqLiteManager.addJournalToDatabase(newJournal);
        } else {
            selectedJournal.setTitle(title);
            selectedJournal.setContent(content);
            sqLiteManager.updateJournalInDB(selectedJournal);
        }


        finish();
        // After saving journal entry

        Toast.makeText(JournalDetails.this, "Entry Saved!", Toast.LENGTH_SHORT).show();
    }

    public void deleteJournal() {
        if (selectedJournal != null) {
            JournalEntryDatabaseHelper sqLiteManager = JournalEntryDatabaseHelper.instanceOfDatabase(this);
            selectedJournal.setDeleted(new Date());
            sqLiteManager.updateJournalInDB(selectedJournal);
            finish();
            Toast.makeText(JournalDetails.this, "Deleted!", Toast.LENGTH_SHORT).show();
        }

    }
}
//entries.add(parseJournalEntry(journalEntries.getJSONObject(i)));