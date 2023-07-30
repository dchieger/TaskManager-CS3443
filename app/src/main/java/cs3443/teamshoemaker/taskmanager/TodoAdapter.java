package cs3443.teamshoemaker.taskmanager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class TodoAdapter extends ArrayAdapter<TodoItem> {
    final Context mContext;
    final ArrayList<TodoItem> todoItems; // added
    public TodoAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
        mContext = context;
        this.todoItems = todoItems; // added
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_todo, parent, false);
        }

        TodoItem currentItem = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.textViewTitle);
        TextView descriptionTextView = listItemView.findViewById(R.id.textViewDescription);

        if (currentItem != null) {
            titleTextView.setText(currentItem.getTitle());
            descriptionTextView.setText(currentItem.getDescription());
        }

        return listItemView;
    }
}