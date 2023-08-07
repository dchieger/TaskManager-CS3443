package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


@SuppressLint("MissingInflatedId")
public class journal extends AppCompatActivity {

    FloatingActionButton addJournalBtn;
    private ListView journalListView;

    FirebaseAuth auth;
    FirebaseUser user;

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

    // For logout


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
                JournalEntry selectedJournal = (JournalEntry) journalListView.getItemAtPosition(position);
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