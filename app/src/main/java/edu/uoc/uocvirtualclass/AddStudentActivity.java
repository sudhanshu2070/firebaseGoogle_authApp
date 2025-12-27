package edu.uoc.uocvirtualclass;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.uoc.uocvirtualclass.model.DataSourceFirebase;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etStudentId, etStudentName;
    private Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etStudentId = findViewById(R.id.etStudentId);
        etStudentName = findViewById(R.id.etStudentName);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(v -> {
            String id = etStudentId.getText().toString();
            String name = etStudentName.getText().toString();

            DataSourceFirebase.getInstance().addNewStudentAsync(
                    id,
                    name,
                    () -> {
                        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                        finish();
                    },
                    e -> Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show()
            );
        });
    }
}
