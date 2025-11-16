package kh.edu.rupp.itemad2demo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kh.edu.rupp.itemad2demo.viewmodels.ProjectViewModel;
import kh.edu.rupp.itemad2demo.views.ProjectAdapter;

public class ProjectsActivity extends AppCompatActivity {

    ProjectViewModel viewModel;
    ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        setupBackButton();
        setupRecycler();
        observeProjects();
    }

    private void setupBackButton() {
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    private void setupRecycler() {
        RecyclerView recycler = findViewById(R.id.recyclerProjects);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProjectAdapter(this, new ArrayList<>());
        recycler.setAdapter(adapter);
    }

    private void observeProjects() {
        viewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        viewModel.getProjects().observe(this, projects -> {
            adapter.updateProjects(projects);
        });
    }
}
