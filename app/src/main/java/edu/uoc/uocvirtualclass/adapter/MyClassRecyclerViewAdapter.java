package edu.uoc.uocvirtualclass.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uoc.uocvirtualclass.R;
import edu.uoc.uocvirtualclass.model.ModStudent;

public class MyClassRecyclerViewAdapter extends RecyclerView.Adapter<MyClassRecyclerViewAdapter.ViewHolder> {

    public interface OnStudentClickListener {
        void onStudentClick(ModStudent student);
    }

    private final List<ModStudent> students;
    private final OnStudentClickListener listener;

    public MyClassRecyclerViewAdapter(List<ModStudent> students, OnStudentClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModStudent student = students.get(position);
        holder.studentName.setText(student.name);
        holder.itemView.setOnClickListener(v -> listener.onStudentClick(student));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.tvStudentName);
        }
    }
}
