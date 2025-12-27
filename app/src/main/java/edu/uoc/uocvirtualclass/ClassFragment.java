package edu.uoc.uocvirtualclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.uoc.uocvirtualclass.adapter.TasksRecyclerViewAdapter;
import edu.uoc.uocvirtualclass.model.DataSourceFirebase;
import edu.uoc.uocvirtualclass.model.ModStudent;
import edu.uoc.uocvirtualclass.model.ModTask;

public class ClassFragment extends Fragment {

    private RecyclerView recyclerView;
    private TasksRecyclerViewAdapter adapter;
    private List<ModTask> tasks = new ArrayList<>();
    private DataSourceFirebase dataSourceFirebase;
    private List<ModStudent> students = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TasksRecyclerViewAdapter(tasks);
        recyclerView.setAdapter(adapter);

        dataSourceFirebase = new DataSourceFirebase();
        loadAllTasks();

        return view;
    }

    private void loadAllTasks() {
        dataSourceFirebase.loadModelAsync(new DataSourceFirebase.LoadCallback() {
            @Override
            public void onLoaded(List<ModStudent> loadedStudents) {
                students.clear();
                students.addAll(loadedStudents);

                tasks.clear();
                for (ModStudent student : students) {
                    dataSourceFirebase.loadTasksAsync(student.id, new DataSourceFirebase.LoadTasksCallback() {
                        @Override
                        public void onLoaded(List<ModTask> loadedTasks) {
                            tasks.addAll(loadedTasks);
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(() ->
                                        Toast.makeText(getContext(), "Failed to load tasks", Toast.LENGTH_SHORT).show());
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Failed to load students", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
