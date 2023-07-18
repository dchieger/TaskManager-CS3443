package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JournalDetails extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageButton saveJournalBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);

        titleEditText= findViewById(R.id.journal_title_text);
        contentEditText= findViewById(R.id.journal_content_text);
        saveJournalBtn= findViewById(R.id.save_journal_btn);

        saveJournalBtn.setOnClickListener( (v)-> {
            try {
                saveJournal();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    //if the tilte is empty
    void saveJournal() throws IOException {
        String journalTitle = titleEditText.getText().toString();
        String journalContent = contentEditText.getText().toString();

        String fileName = System.currentTimeMillis() + ".txt";

        if(journalTitle == null || journalTitle.isEmpty() ) {
            titleEditText.setError("Title is required");
            return;
        }
            File path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(journalContent.getBytes());
            outputStream.close();
            finish();

        }

    }
