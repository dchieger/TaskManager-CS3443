package cs3443.teamshoemaker.taskmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton logoutButtonIcon, JournalIcon, listIcon, calendarIcon;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        // For logout
        auth = FirebaseAuth.getInstance();
        ImageButton logoutButtonIcon = findViewById(R.id.logoutButtonIcon);
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
        logoutButtonIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }

        });

        //Nav bar icons
        // find views
        ImageButton JournalIcon = findViewById(R.id.journalIcon);
        ImageButton listIcon = findViewById(R.id.ListIcon);
        ImageButton calendarIcon = findViewById(R.id.CalendarIcon);

        // Set the click listener for each icon
        JournalIcon.setOnClickListener(this);
        listIcon.setOnClickListener(this);
        calendarIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handle the click events based on the view's ID
        switch (v.getId()) {
            case R.id.journalIcon:
                // Handle the click for the journal icon
                // For example, navigate to the journal activity
                startActivity(new Intent(this, journal.class));
                break;

            case R.id.ListIcon:
                // Handle the click for the list icon
                // For example, navigate to the list activity
                startActivity(new Intent(this, todoList.class));
                break;

            case R.id.CalendarIcon:
                // Handle the click for the calendar icon
                // For example, navigate to the calendar activity
                startActivity(new Intent(this, CalendarActivity.class));
                break;

            default:
                break;
        }
    }
}
