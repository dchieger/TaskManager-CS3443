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
    // Adapter arraylist for todoItems
    ArrayList<TodoItem> todoItems = new ArrayList<>(); // before
    TodoAdapter todoAdapter;


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
        floatingPlusButton    = findViewById(R.id.floatingPlusButton);
        logoutButton = findViewById(R.id.logoutButton);
        ListButton= findViewById(R.id.ListButton);
        // Use the class member variable
        ListView listViewTasks = (ListView) findViewById(R.id.listview_Tasks);

        // Initialize the todoItems list
        // Initialize todoItems list and todoAdapter
        todoAdapter = new TodoAdapter(this, todoItems);

        // Set the custom adapter to the ListView
        listViewTasks.setAdapter(todoAdapter);


        // Get the logged-in user's email from intent in todolist
        Intent intent = getIntent();
        if (intent != null) {
            loggedUserEmail = intent.getStringExtra("email");
            Log.d("todoList", "Logged User Email: " + loggedUserEmail);
        } else {
            // Handle the case when loggedUserEmail is not passed in the intent
            Toast.makeText(this, "No user email found.", Toast.LENGTH_SHORT).show();

        }

        // Call the loadTasks method with the logged-in user's email as the argument
        loadTasks(loggedUserEmail);

        journalButton.setOnClickListener((v) -> startActivity(new Intent(todoList.this, journal.class)));
        CalendarButton.setOnClickListener((v) -> startActivity(new Intent(todoList.this, CalendarActivity.class)));
        floatingPlusButton.setOnClickListener((v) ->startActivity(new Intent(todoList.this, addingTask.class)));
        logoutButton.setOnClickListener((v) ->startActivity(new Intent(todoList.this, LoginActivity.class)));
       // ListButton.setOnClickListener((v) ->startActivity(new Intent(todoList.this, todoList.class)));
//        // CHECK ID FOR USER AND FOR TAG  ---- check changes-----
//        floatingPlusButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // startActivity((new Intent(todoList.this, addingTask.class)));
//
//
//            }
//
//        });

    }

    public void loadTasks(String userEmail) {
        String taskJson;
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("taskDB.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            taskJson = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONObject taskData = new JSONObject(taskJson);
            JSONArray taskArray = taskData.getJSONArray("Tasks");

            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject taskObject = taskArray.getJSONObject(i);
                String userEmailInTaskDB = taskObject.optString("email", "");

                if (userEmail.equals(userEmailInTaskDB)) {
                    // load the logged-in user's task
                    String title = taskObject.getString("title");
                    String description = taskObject.getString("description");
                    TodoItem todoItem = new TodoItem(title, description);
                    todoItems.add(todoItem);
                }
            }

            // Notify the adapter of the data change
            todoAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}