package edu.uoc.uocvirtualclass.model;

import java.util.ArrayList;
import java.util.List;

public abstract class DataSource {

    public enum DataSourceType {
        HARDCODED,JSON,SQL,FIRESTORE
    }


    public  List<ModStudent> STUDENTS = new ArrayList<>();

    protected static DataSource instance;

    protected DataSource() {}

    public abstract void loadModelAsync(DataSourceFirebase.LoadCallback callback);

    public abstract void loadModel();
    public abstract  void loadStudent(ModStudent student);
    public abstract List<ModStudent> getStudents();
    public abstract List<ModTask> getTasks(String student_id);
    public abstract ModStudent getStudent(String student_id);

    public static DataSource getInstance() {
        return instance;
    }

}
