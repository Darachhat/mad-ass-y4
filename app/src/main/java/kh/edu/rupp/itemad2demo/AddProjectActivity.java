package kh.edu.rupp.itemad2demo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.util.Calendar;

import kh.edu.rupp.itemad2demo.viewmodels.ProjectViewModel;

public class    AddProjectActivity extends AppCompatActivity {

    private static final int PICK_LOGO = 101;

    ImageView btnBack, imgLogo;
    TextView textStartDate, textEndDate;
    EditText editProjectName, editDescription;
    Spinner spinnerGroup;
    Button btnAddProject, btnChangeLogo;

    ProjectViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        initViews();
        setupButtons();

        viewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        imgLogo = findViewById(R.id.imgLogo);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);
        editProjectName = findViewById(R.id.editProjectName);
        editDescription = findViewById(R.id.editDescription);
        spinnerGroup = findViewById(R.id.spinnerGroup);
        btnAddProject = findViewById(R.id.btnAddProject);
        btnChangeLogo = findViewById(R.id.btnChangeLogo);
    }

    private void setupButtons() {

        // back button
        btnBack.setOnClickListener(v -> finish());

        // change logo
        btnChangeLogo.setOnClickListener(v -> pickLogo());

        // Start date picker
        textStartDate.setOnClickListener(v -> openDatePicker(textStartDate));

        // End date picker
        textEndDate.setOnClickListener(v -> openDatePicker(textEndDate));

        // Add project
        btnAddProject.setOnClickListener(v -> saveProject());
    }

    private void pickLogo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_LOGO);
    }

    private void openDatePicker(TextView target) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int day) -> {
                    String formatted = day + " / " + (month + 1) + " / " + year;
                    target.setText(formatted);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_LOGO && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imgLogo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProject() {

        String name = editProjectName.getText().toString().trim();
        String desc = editDescription.getText().toString().trim();
        String start = textStartDate.getText().toString();
        String end = textEndDate.getText().toString();
        String group = spinnerGroup.getSelectedItem().toString();

        if (name.isEmpty()) {
            editProjectName.setError("Enter project name");
            return;
        }

        viewModel.addProject(name, desc, group, start, end);

        finish(); // go back
    }
}
