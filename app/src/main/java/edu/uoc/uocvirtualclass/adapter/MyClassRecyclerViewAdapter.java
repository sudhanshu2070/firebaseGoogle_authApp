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

public class MyClassRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final List<ModStudent> students;

    public MyClassRecyclerViewAdapter(List<ModStudent> students) {
        this.students = students;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_student_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_student, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ModStudent student = students.get(position - 1);
            ((ItemViewHolder) holder).studentId.setText(student.id);
            ((ItemViewHolder) holder).studentName.setText(student.name);
            ((ItemViewHolder) holder).studentLevel.setText(String.valueOf(student.level));
        }
    }

    @Override
    public int getItemCount() {
        return students.size() + 1;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView studentId, studentName, studentLevel;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.tvStudentId);
            studentName = itemView.findViewById(R.id.tvStudentName);
            studentLevel = itemView.findViewById(R.id.tvStudentLevel);
        }
    }
}
