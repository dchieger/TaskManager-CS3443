package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JournalDetails extends AppCompatActivity {
    public static class JournalEntry {

        private String title;
        private String content;
        private long timestamp;

        public JournalEntry(String title, String content, long timestamp) {

            this.title = title;
            this.content = content;
            this.timestamp = timestamp;
        }

        // Getters and setters for the properties

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }


    EditText titleEditText, contentEditText;
    ImageButton saveJournalBtn;
    JSONArray journalEntries = new JSONArray();



    // Instance variables for the database helper and database
    private JournalEntryDatabaseHelper databaseHelper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);

        // Initialize the database helper
        databaseHelper = new JournalEntryDatabaseHelper(this);

        // Initialize the database instance
        database = databaseHelper.getWritableDatabase();



        titleEditText= findViewById(R.id.journal_title_text);
        contentEditText= findViewById(R.id.journal_content_text);
        saveJournalBtn= findViewById(R.id.save_journal_btn);

        saveJournalBtn.setOnClickListener( (v)-> {
            try {
                saveJournal();

            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        });



    }


    private JournalEntry parseJournalEntry(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        long timestamp = jsonObject.getLong("timestamp");

        // Create and return the JournalEntry object
        return new JournalEntry(title, content, timestamp);
    }
    //if the title is empty
    void saveJournal() throws IOException, JSONException {
        String journalTitle = titleEditText.getText().toString();
        String journalContent = contentEditText.getText().toString();


        JSONObject entry = new JSONObject();
        entry.put("title", journalTitle);
        entry.put("content", journalContent);
        entry.put("timestamp", System.currentTimeMillis());

        journalEntries.put(entry);

        String fileName = System.currentTimeMillis() + ".txt";


        if (journalTitle == null || journalTitle.isEmpty()) {
            titleEditText.setError("Title is required");
            return;
        }



        // Insert the journal entry into the database
        ContentValues values = new ContentValues();
        values.put(JournalEntryDatabaseHelper.COLUMN_TITLE, journalTitle);
        values.put(JournalEntryDatabaseHelper.COLUMN_CONTENT, journalContent);
        values.put(JournalEntryDatabaseHelper.COLUMN_TIMESTAMP, System.currentTimeMillis());

        database = databaseHelper.getWritableDatabase();
       // long newRowId = database.insert(JournalEntryDatabaseHelper.TABLE_NAME, null, values);
        database.close();

        finish();
        Snackbar.make(findViewById(android.R.id.content), "Entry Saved!", Snackbar.LENGTH_SHORT).show();
        List<JournalEntry> entries = new ArrayList<JournalEntry>();



        for (int i = 0; i < journalEntries.length(); i++) {
            // Parse JournalEntry object from each JSONObject
            JSONObject jsonObject = journalEntries.getJSONObject(i);
            JournalEntry parsedEntry = parseJournalEntry(jsonObject);
            entries.add(parsedEntry);
        }
    }



    }
//entries.add(parseJournalEntry(journalEntries.getJSONObject(i)));