package cs3443.teamshoemaker.taskmanager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;

public class JournalEntryAdapter extends ArrayAdapter<JournalEntry> {
    public JournalEntryAdapter(Context context, List<JournalEntry> journals) {
        super(context, R.layout.list_journal, journals);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JournalEntry journal = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_journal, parent, false);

        TextView title = convertView.findViewById(R.id.titleTextView);
        TextView content = convertView.findViewById(R.id.contentTextView);

        title.setText(journal.getTitle());
        content.setText(journal.getContent());

        return convertView;
    }
}