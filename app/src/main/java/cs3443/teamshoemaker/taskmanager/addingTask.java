package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class addingTask extends AppCompatActivity {

    EditText taskName;
    EditText taskDescription;
    Button addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);


        taskName = findViewById(R.id.editTextTaskName);
        taskDescription = findViewById(R.id.task_description);
        addTask = findViewById(R.id.addTaskbutton);


        // Set a click listener on the addTask button
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String TName = taskName.getText().toString();
                String TDescription = taskDescription.getText().toString();

                // Create JSON object for the new task
                JSONObject newTask = new JSONObject();
                try {
                    newTask.put("title", TName);
                    newTask.put("description", TDescription);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Load existing tasks from the file
                String filename = "taskDB.json";
                JSONArray existingTasks = new JSONArray();
                try {
                    FileInputStream fis = openFileInput(filename);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    fis.close();
                    existingTasks = new JSONArray(stringBuilder.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                // Add the new task to the existing tasks
                existingTasks.put(newTask);

                // Write the updated tasks back to the file
                try (FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)) {
                    fos.write(existingTasks.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Show a success message
                Toast.makeText(addingTask.this, "Task added successfully!", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                taskName.setText("");
                taskDescription.setText("");
            }
        });

    }
}
