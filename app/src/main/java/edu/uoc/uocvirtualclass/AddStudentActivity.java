package edu.uoc.uocvirtualclass;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editName, editId;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editName = findViewById(R.id.editName);
        editId = findViewById(R.id.editId);
        btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String id = editId.getText().toString();
        });
    }
}
