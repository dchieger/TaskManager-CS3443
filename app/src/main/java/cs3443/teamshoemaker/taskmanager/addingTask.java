package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;


public class addingTask extends AppCompatActivity {

    EditText taskName;
    EditText taskDescription;
    Button addTask;

    FirebaseFirestore db;
    String loggedInUserEmail;

    ImageView next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);


        db = FirebaseFirestore.getInstance();

        // Get the logged-in user's email from the intent
        loggedInUserEmail = getIntent().getStringExtra("userEmail");

        taskName = findViewById(R.id.editTextTaskName);
        taskDescription = findViewById(R.id.task_description);
        addTask = findViewById(R.id.addTaskbutton);
        next = findViewById(R.id.nextArrow);

        // Set a click listener on the addTask button
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String taskTitle = taskName.getText().toString();
                String taskDesc = taskDescription.getText().toString();

                // Check if the task title and description are not empty
                if (!taskTitle.isEmpty() && !taskDesc.isEmpty()) {
                    // Add the task to Fire Store
                    addTaskToFirestore(loggedInUserEmail, taskTitle, taskDesc);
                } else {
                    Toast.makeText(addingTask.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addingTask.this,todoList.class);
                startActivity(intent);
            }
        });
    }


    private void addTaskToFirestore(String userEmail, String taskTitle, String taskDesc) {
        if (userEmail == null) {
            // Handle the case where the userEmail is null
            Log.e("AddingTask", "User email is null.");
            Toast.makeText(this, "User email is null. Unable to add task.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get a reference to the "users" collection
        CollectionReference usersCollectionRef = db.collection("users");

        // Find the user document with the email (assuming email is unique)
        DocumentReference userDocRef = usersCollectionRef.document(userEmail);

        // Get a reference to the "tasks" sub collection for the user
        CollectionReference tasksCollectionRef = userDocRef.collection("tasks");

        // Create a Map to represent the task data
        Map<String, Object> taskData = new HashMap<>();
        taskData.put("title", taskTitle);
        taskData.put("description", taskDesc);

        // Add a new task document to the "tasks" sub collection for the user
        tasksCollectionRef.add(taskData)
                .addOnSuccessListener(documentReference -> {
                    // Task added successfully
                    String taskId = documentReference.getId();
                    Toast.makeText(addingTask.this, "Task added successfully " , Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure (e.g., show an error message)
                    Toast.makeText(addingTask.this, "Error adding task: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AddingTask", "Error adding task", e);
                });
    }
}