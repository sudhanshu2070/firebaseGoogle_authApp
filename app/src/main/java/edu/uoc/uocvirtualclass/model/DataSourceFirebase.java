package edu.uoc.uocvirtualclass.model;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;

public class DataSourceFirebase extends DataSource {

    private static DataSourceFirebase instance;

    public DataSourceFirebase() { }

    public static synchronized DataSourceFirebase getInstance() {
        if (instance == null) {
            instance = new DataSourceFirebase();
        }
        return instance;
    }

    public interface LoadCallback {
        void onLoaded(List<ModStudent> students);
        void onError(Exception e);
    }

    public interface LoadTasksCallback {
        void onLoaded(List<ModTask> tasks);
        void onError(Exception e);
    }

    public void loadModelAsync(LoadCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Student")
                .get(Source.SERVER)
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    STUDENTS = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String firebaseId = doc.getId();
                        String studentId = doc.getString("id");
                        String studentName = doc.getString("name");
                        Long studentLevel = doc.getLong("level");
                        if (studentId != null && studentName != null && studentLevel != null) {
                            STUDENTS.add(new ModStudent(firebaseId, studentId, studentName, studentLevel.intValue()));
                        }
                    }
                    callback.onLoaded(STUDENTS);
                })
                .addOnFailureListener(callback::onError);
    }

    public void loadTasksAsync(String studentId, LoadTasksCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("task")
                .whereEqualTo("student_id", studentId)
                .get(Source.SERVER)
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<ModTask> tasks = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String taskId = doc.getString("task_id");
                        String taskName = doc.getString("task_name");
                        Boolean bValue = doc.getBoolean("b_value");
                        if (bValue == null) bValue = false;

                        tasks.add(new ModTask(taskId, taskName, bValue));
                    }
                    callback.onLoaded(tasks);
                })
                .addOnFailureListener(callback::onError);
    }

    @Override
    public void loadModel() {  }

    @Override
    public void loadStudent(ModStudent student) {  }

    @Override
    public List<ModStudent> getStudents() {
        return STUDENTS;
    }

    @Override
    public ModStudent getStudent(String student_id) {
        for (ModStudent student : STUDENTS) {
            if (student.id.equals(student_id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<ModTask> getTasks(String student_id) {
        return new ArrayList<>();
    }
}
