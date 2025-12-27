package edu.uoc.uocvirtualclass;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ClassFragment())
                .commit();
    }
}
