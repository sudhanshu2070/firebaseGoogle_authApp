package edu.uoc.uocvirtualclass;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ClassFragment())
                    .commit();
        }

        findViewById(R.id.btnAddStudent).setOnClickListener(v ->
                startActivity(new Intent(this, AddStudentActivity.class))
        );
    }
}
