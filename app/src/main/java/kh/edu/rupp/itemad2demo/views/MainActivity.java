package kh.edu.rupp.itemad2demo.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import kh.edu.rupp.itemad2demo.R;
import kh.edu.rupp.itemad2demo.viewmodels.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel viewModel;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTask = findViewById(R.id.editTask);
        Button btnAdd = findViewById(R.id.btnAdd);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        adapter = new TaskAdapter();

        androidx.recyclerview.widget.RecyclerView rv = findViewById(R.id.recyclerTasks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        // observe list
        viewModel.getTasks().observe(this, adapter::setItems);

        // full-model addTask
        btnAdd.setOnClickListener(v -> {
            String title = editTask.getText().toString().trim();
            if (!title.isEmpty()) {
                viewModel.addTask(title);
                editTask.setText("");
            }
        });

        // optional: demo list
        viewModel.seedDemo();
    }
}
