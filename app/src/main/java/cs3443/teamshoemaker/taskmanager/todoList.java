package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;


public class todoList extends AppCompatActivity {

private Button plusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        // Date display
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.floatingPlusButton);
        plusButton.setOnClickListener(new OnClickListener(){
              @Override
            public void onClick(View v){
                  startActivity((new Intent(todoList.this, addingTask.class)));
              }

        });

    }
}