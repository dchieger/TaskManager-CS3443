package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class journal extends AppCompatActivity {

    FloatingActionButton addJournalBtn;
    RecyclerView recyclerViewJournalEntries; // Add RecyclerView reference
    JournalEntryAdapter journalEntryAdapter; // Add JournalEntryAdapter reference
    ArrayList<JournalDetails.JournalEntry> journalEntriesList = new ArrayList<>(); // Add list to hold journal entries
    SQLiteDatabase database; // Declare the database variable
    JournalEntryDatabaseHelper databaseHelper; // Declare the database helper variable



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        databaseHelper = new JournalEntryDatabaseHelper(this);


        //fetch
        List<JournalDetails.JournalEntry> entries = getJournalEntriesFromDatabase();
        updateRecyclerView(entries);


        addJournalBtn = findViewById(R.id.add_journal_btn);
        recyclerViewJournalEntries = findViewById(R.id.recyclerViewJournalEntries); // Initialize RecyclerView

        addJournalBtn.setOnClickListener((v)-> startActivity(new Intent(journal.this,JournalDetails.class)) );
        // Set up the RecyclerView with the adapter






        recyclerViewJournalEntries.setAdapter(journalEntryAdapter);
    }

    // Method to get all journal entries from the database
    private List<JournalDetails.JournalEntry> getJournalEntriesFromDatabase() {
        List<JournalDetails.JournalEntry> entries = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();

        // Query the database to get all rows from the journal_entries table
        Cursor cursor = database.query(
                JournalEntryDatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        // Loop through the cursor and create JournalEntry objects from the rows
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntryDatabaseHelper.COLUMN_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(JournalEntryDatabaseHelper.COLUMN_CONTENT));
            long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(JournalEntryDatabaseHelper.COLUMN_TIMESTAMP));

            JournalDetails.JournalEntry entry = new JournalDetails.JournalEntry( title, content, timestamp);
            entries.add(entry);
        }

        // Close the cursor and database
        cursor.close();
        database.close();

        return entries;
    }

    // Method to update the RecyclerView with the new list of journal entries
    private void updateRecyclerView(List<JournalDetails.JournalEntry> entries) {
        // Set up the RecyclerView and JournalEntryAdapter
        RecyclerView recyclerView = findViewById(R.id.recyclerViewJournalEntries);
        JournalEntryAdapter adapter = new JournalEntryAdapter(journalEntriesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}