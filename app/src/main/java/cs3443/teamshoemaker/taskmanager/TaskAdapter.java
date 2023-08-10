package cs3443.teamshoemaker.taskmanager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    Context context;
    private String loggedUserEmail;
    FirebaseFirestore db;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter; // Declare the adapter variable
    private ArrayList<Tasks> tasksList = new ArrayList<>();


    public TaskAdapter(Context context, ArrayList<Tasks> tasksList) {
        this.tasksList = tasksList;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }


    // Inside your todoList activity
    private void fetchTasksFromFirestore() {

        // Get a reference to the tasks collection for the user
        CollectionReference tasksCollectionRef = db.collection("users")
                .document(loggedUserEmail)
                .collection("tasks");

        // Query the tasks collection
        tasksCollectionRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    tasksList.clear(); // Clear the current tasksList

                    // Populate the tasksList with the fetched tasks
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String title = document.getString("title");
                        String description = document.getString("description");
                        Tasks task = new Tasks(title, description);
                        tasksList.add(task);
                    }

                    // Notify the adapter about the data change
                    taskAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle the failure to fetch tasks (e.g., show an error message)
                    Log.e("TodoList", "Error fetching tasks", e);
                });
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