package cs3443.teamshoemaker.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddingEvent extends AppCompatActivity {

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_event);

        // Date display
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.event_view_date);
        textViewDate.setText(currentDate);
    }
}


