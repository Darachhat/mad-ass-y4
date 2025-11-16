package kh.edu.rupp.itemad2demo.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.R;
import kh.edu.rupp.itemad2demo.databinding.ActivityMainBinding;
import kh.edu.rupp.itemad2demo.models.Task;
import kh.edu.rupp.itemad2demo.viewmodels.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TaskViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        binding.setViewModel(viewModel);

        binding.setLifecycleOwner(this);


    }

}
