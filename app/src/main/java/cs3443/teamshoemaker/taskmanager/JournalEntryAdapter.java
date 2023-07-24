package cs3443.teamshoemaker.taskmanager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.text.SimpleDateFormat;
import java.util.Date;


public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.ViewHolder> {
    private List<JournalDetails.JournalEntry> journalEntries;

    public JournalEntryAdapter(List<JournalDetails.JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_journal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JournalDetails.JournalEntry entry = journalEntries.get(position);

        // Bind the data to the views in the journal.xml layout
        holder.titleTextView.setText(entry.getTitle());
        holder.contentTextView.setText(entry.getContent());

        // Convert timestamp to human-readable format
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String formattedTimestamp = sdf.format(new Date(entry.getTimestamp()));
        holder.timestampTextView.setText(formattedTimestamp);
    }

    @Override
    public int getItemCount() {
        return journalEntries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, timestampTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.journal_title_text);
            contentTextView = itemView.findViewById(R.id.journal_content_text);
            timestampTextView = itemView.findViewById(R.id.journal_timestamp_text);
        }
    }
}
