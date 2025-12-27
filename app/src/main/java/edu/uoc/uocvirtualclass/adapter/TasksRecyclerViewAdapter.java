package edu.uoc.uocvirtualclass.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uoc.uocvirtualclass.R;
import edu.uoc.uocvirtualclass.model.ModTask;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private final List<ModTask> tasks;

    public TasksRecyclerViewAdapter(List<ModTask> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModTask task = tasks.get(position);
        holder.tvTaskId.setText("Task ID: " + task.firebase_id);
        holder.tvTaskName.setText("Task name: " + task.name);
        holder.checkTaskCompleted.setText(String.valueOf(task.completed));

        holder.checkTaskCompleted.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskId, tvTaskName, checkTaskCompleted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskId = itemView.findViewById(R.id.tvTaskId);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            checkTaskCompleted = itemView.findViewById(R.id.tvTaskCompleted);
        }
    }
}
