package kh.edu.rupp.itemad2demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Task;
import kh.edu.rupp.itemad2demo.viewmodels.TaskViewModel;
import kh.edu.rupp.itemad2demo.views.TaskAdapter;

public class MainActivity extends AppCompatActivity {

    TaskViewModel viewModel;
    TaskAdapter adapter;

    Button tabAll, tabTodo, tabProgress, tabDone;
    ImageView btnAddTask;

    List<Task> allTasks = new ArrayList<>(); // full list for filtering

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupBottomNavigation();
        setupFilters();
        setupAddButton();
        observeTaskData();
    }

    private void setupRecyclerView() {
        RecyclerView recycler = findViewById(R.id.recyclerTasks);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(this, new ArrayList<>());
        recycler.setAdapter(adapter);
    }
    private void setupBottomNavigation() {

        ImageView navHome = findViewById(R.id.navHome);
        ImageView navCalendar = findViewById(R.id.navCalendar);
        ImageView navAdd = findViewById(R.id.navAdd);
        ImageView navProjects = findViewById(R.id.navProjects);
        ImageView navProfile = findViewById(R.id.navProfile);

        navHome.setOnClickListener(v -> {
            // Already on Home — do nothing or refresh
        });

        navCalendar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CalendarActivity.class));
        });

        navAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddProjectActivity.class));
        });

        navProjects.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProjectsActivity.class));
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });
    }


    private void setupFilters() {
        tabAll = findViewById(R.id.tabAll);
        tabTodo = findViewById(R.id.tabTodo);
        tabProgress = findViewById(R.id.tabProgress);
        tabDone = findViewById(R.id.tabDone);

        tabAll.setOnClickListener(v -> filterTasks("All"));
        tabTodo.setOnClickListener(v -> filterTasks("To-do"));
        tabProgress.setOnClickListener(v -> filterTasks("In Progress"));
        tabDone.setOnClickListener(v -> filterTasks("Done"));
    }

    private void setupAddButton() {
        btnAddTask = findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(v -> {
            // Here you can open Add Project activity or Add Task activity
            // Example:
            // startActivity(new Intent(MainActivity.this, AddProjectActivity.class));
        });
    }

    private void observeTaskData() {
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        viewModel.getTasks().observe(this, tasks -> {
            allTasks = tasks; // save full list for filtering
            adapter.updateTasks(tasks);
        });
    }

    private void filterTasks(String type) {

        if (type.equals("All")) {
            adapter.updateTasks(allTasks);
            return;
        }

        List<Task> filtered = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getStatus().equals(type)) {
                filtered.add(task);
            }
        }

        adapter.updateTasks(filtered);
    }
}
