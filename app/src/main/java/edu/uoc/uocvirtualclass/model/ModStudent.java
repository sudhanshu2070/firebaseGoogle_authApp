package edu.uoc.uocvirtualclass.model;

import java.util.List;

public class ModStudent {
    public String firebase_id;
    public String id;
    public String name;
    public int level;
    public List<ModTask> tasks;

    public ModStudent(String firebase_id, String id, String name, int level) {
        this.firebase_id = firebase_id;
        this.id = id;
        this.name = name;
        this.level = level;
    }
}
