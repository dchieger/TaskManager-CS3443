package cs3443.teamshoemaker.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    Context context;
    private ArrayList<Tasks> tasksList;

    public TaskAdapter(Context context, ArrayList<Tasks> tasksList) {
        this.tasksList = tasksList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        return new TaskViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tasks tasks = tasksList.get(position);
        holder.title.setText(tasks.getTitle());
        holder.description.setText(tasks.getDescription());
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.TitleTask);
            description = itemView.findViewById(R.id.DescriptionTask);
        }
    }


}
