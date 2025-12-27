package edu.uoc.uocvirtualclass.model;

public class ModTask {

    public String firebase_id;
    public String name;
    public boolean completed;


    public ModTask(String taskId, String taskName, Boolean bValue) {
        this.firebase_id = taskId;
        this.name = taskName;
        this.completed = bValue != null && bValue;
    }

}
