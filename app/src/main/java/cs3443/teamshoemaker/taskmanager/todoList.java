package cs3443.teamshoemaker.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.text.DateFormat;
import java.util.Calendar;



public class todoList extends AppCompatActivity implements OnClickListener {

    private FloatingActionButton floatingPlusButton;
    private ImageButton journalButton;
    private ImageButton CalendarButton;
    private ImageButton ListButton;
    private ImageButton logoutButton;
    FirebaseAuth auth;
    FirebaseUser user;
    ImageButton logoutButtonIcon, JournalIcon, listIcon, calendarIcon;

    private String loggedUserEmail; // Variable to store the email of the currently logged-in user

    String title, description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // error ??
        setContentView(R.layout.activity_todo_list);


        // Date display
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        // Find views by id
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.floatingPlusButton);

        journalButton = findViewById(R.id.journalButton);
        CalendarButton = findViewById(R.id.CalendarButton);
        floatingPlusButton = findViewById(R.id.floatingPlusButton);
        logoutButton = findViewById(R.id.logoutButton);
        ListButton = findViewById(R.id.ListButton);


        // For logout
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }

        });


        floatingPlusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(todoList.this,addingTask.class);
                startActivity(intent);
            }
        });


        //Nav bar icons
        // find views
        ImageButton JournalIcon = findViewById(R.id.journalButton);
        ImageButton listIcon = findViewById(R.id.ListButton);
        ImageButton calendarIcon = findViewById(R.id.CalendarButton);

        // Set the click listener for each icon
        JournalIcon.setOnClickListener(this);
        listIcon.setOnClickListener(this);
        calendarIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handle the click events based on the view's ID
        switch (v.getId()) {
            case R.id.journalButton:
                // Handle the click for the journal icon
                // For example, navigate to the journal activity
                startActivity(new Intent(this, journal.class));
                break;

            case R.id.ListButton:
                // Handle the click for the list icon
                // For example, navigate to the list activity
                startActivity(new Intent(this, todoList.class));
                break;

            case R.id.CalendarButton:
                // Handle the click for the calendar icon
                // For example, navigate to the calendar activity
                startActivity(new Intent(this, CalendarActivity.class));
                break;

            default:
                break;
        }
    }
}


