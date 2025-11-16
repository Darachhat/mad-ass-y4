package kh.edu.rupp.itemad2demo.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import kh.edu.rupp.itemad2demo.R;

public class AddProjectActivity extends AppCompatActivity {

    private TextView textStartDate, textEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        EditText editProjectName = findViewById(R.id.editProjectName);
        EditText editDescription = findViewById(R.id.editDescription);

        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);

        Button btnAddProject = findViewById(R.id.btnAddProject);
        ImageView btnBack = findViewById(R.id.btnBack);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Spinner items
        String[] groups = {"Work", "Personal", "Study", "Shopping"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, groups);
        spinnerGroup.setAdapter(adapter);

        // Date pickers
        textStartDate.setOnClickListener(v -> showDatePicker(textStartDate));
        textEndDate.setOnClickListener(v -> showDatePicker(textEndDate));

        btnAddProject.setOnClickListener(v -> {
            Toast.makeText(this, "Project Added!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void showDatePicker(TextView target) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            String date = String.format("%02d %s, %d", day, getMonthName(month), year);
            target.setText(date);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private String getMonthName(int month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[month];
    }
}
