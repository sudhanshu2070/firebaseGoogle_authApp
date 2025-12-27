package edu.uoc.uocvirtualclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.uoc.uocvirtualclass.adapter.TasksRecyclerViewAdapter;
import edu.uoc.uocvirtualclass.model.DataSourceFirebase;
import edu.uoc.uocvirtualclass.model.ModTask;

public class TasksFragment extends Fragment {

    private static final String ARG_STUDENT_ID = "student_id";
    private String studentId;

    private RecyclerView recyclerView;
    private TasksRecyclerViewAdapter adapter;
    private List<ModTask> tasks = new ArrayList<>();

    public TasksFragment() {}

    public static TasksFragment newInstance(String studentId) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STUDENT_ID, studentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getString(ARG_STUDENT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TasksRecyclerViewAdapter(tasks);
        recyclerView.setAdapter(adapter);

        loadTasksFromFirebase();

        return view;
    }

    private void loadTasksFromFirebase() {
        DataSourceFirebase.getInstance().loadTasksAsync(studentId, new DataSourceFirebase.LoadTasksCallback() {
            @Override
            public void onLoaded(List<ModTask> loadedTasks) {
                tasks.clear();
                tasks.addAll(loadedTasks);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
