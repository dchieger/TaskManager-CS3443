package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import cs3443.teamshoemaker.taskmanager.JournalEntry;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;




@SuppressLint("MissingInflatedId")
public class journal extends AppCompatActivity {


    FloatingActionButton addJournalBtn;
    private ListView journalListView;

     // Flag to track if the list is already populated
    private static boolean isListPopulated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        initWidgets();

        // Check if the journal entries are already loaded
        if (!isListPopulated) {
            loadFromDBToMemory();
            setJournalAdapter();
            isListPopulated = true; // Set the flag to true to indicate the list is already populated
        }

        setOnClickListener();

        addJournalBtn = findViewById(R.id.add_journal_btn);
        addJournalBtn.setOnClickListener((v) -> startActivity(new Intent(journal.this, JournalDetails.class)));
    }



//if (!isListPopulated) {
//            // Fetch entries from the database and populate the list
//            List<JournalEntry> entries = getJournalEntriesFromDatabase();
//            JournalEntry.journalArrayList.addAll(entries);
//            isListPopulated = true; // Set the flag to true to indicate the list is already populated
//        }



    private void initWidgets() {
        journalListView = findViewById(R.id.journalListView);
    }

    private void loadFromDBToMemory() {
        JournalEntryDatabaseHelper sqLiteManager = JournalEntryDatabaseHelper.instanceOfDatabase(this);
        sqLiteManager.populateJournalListArray();
    }

    private void setJournalAdapter() {
        JournalEntryAdapter journalAdapter = new JournalEntryAdapter(getApplicationContext(), JournalEntry.nonDeletedJournal());
        journalListView.setAdapter(journalAdapter);
    }

    private void setOnClickListener() {
        journalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                JournalEntry
                        selectedJournal = (JournalEntry) journalListView.getItemAtPosition(position);
                Intent editJournalIntent = new Intent(getApplicationContext(), JournalDetails.class);
                editJournalIntent.putExtra(JournalEntry.JOURNAL_EDIT_EXTRA, selectedJournal.getId());
                startActivity(editJournalIntent);
            }
        });


    }


    public void newJournal(View view) {
        Intent newJournalIntent = new Intent(this, JournalDetails.class);
        startActivity(newJournalIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setJournalAdapter();
    }


}