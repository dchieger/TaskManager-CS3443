package cs3443.teamshoemaker.taskmanager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;



public class todoList extends AppCompatActivity {

    private FloatingActionButton floatingPlusButton;
    private ImageButton journalButton;
    private ImageButton CalendarButton;
    private ImageButton ListButton;
    private ImageButton logoutButton;

    private String loggedUserEmail; // Variable to store the email of the currently logged-in user
    private JSONObject loggedInUser; // Variable to store the JSON object of the currently logged-in user
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

        floatingPlusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(todoList.this,addingTask.class);
                startActivity(intent);
            }
        });
    }

}

