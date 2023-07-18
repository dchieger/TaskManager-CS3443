package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class journal extends AppCompatActivity {

    FloatingActionButton addJournalBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        addJournalBtn = findViewById(R.id.add_journal_btn);

        addJournalBtn.setOnClickListener((v)-> startActivity(new Intent(journal.this,JournalDetails.class
        )) );
    }
}