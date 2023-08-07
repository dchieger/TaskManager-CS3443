package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


@SuppressLint("MissingInflatedId")
public class journal extends AppCompatActivity {

    FloatingActionButton addJournalBtn;
    private ListView journalListView;
    // FOR NAV BAR
    private ImageButton journalButton, CalendarButton, ListButton, logoutButton;


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


        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }

        });


        //Nav bar icons
        // find views
        ImageButton JournalIcon = findViewById(R.id.journalButton);
        ImageButton listIcon = findViewById(R.id.ListButton);
        ImageButton calendarIcon = findViewById(R.id.CalendarButton);

        // Set the click listener for each icon
        // had to do individually
        JournalIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(journal.this, journal.class));

            }
        });


        listIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(journal.this, todoList.class));
                }
        });
           calendarIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(journal.this, CalendarActivity.class));
                    }
           });


    }




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