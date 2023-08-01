package cs3443.teamshoemaker.taskmanager;

import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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



    }
}
