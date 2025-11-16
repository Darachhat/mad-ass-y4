package kh.edu.rupp.itemad2demo.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Task;

public class TaskViewModel extends ViewModel {

    private final MutableLiveData<List<Task>> taskList = new MutableLiveData<>();

    public TaskViewModel() {
        loadSampleTasks();
    }

    public MutableLiveData<List<Task>> getTasks() {
        return taskList;
    }

    private void loadSampleTasks() {
        List<Task> tasks = new ArrayList<>();

        // EXACT tasks from your screenshot
        tasks.add(new Task(
                "Grocery shopping app design",
                "Market Research",
                "10:00 AM",
                "Done"
        ));

        tasks.add(new Task(
                "Grocery shopping app design",
                "Competitive Analysis",
                "12:00 PM",
                "In Progress"
        ));

        tasks.add(new Task(
                "Uber Eats redesign challenge",
                "Create Low-fidelity Wireframe",
                "07:00 PM",
                "To-do"
        ));

        tasks.add(new Task(
                "About design sprint",
                "How to pitch a Design Sprint",
                "09:00 PM",
                "To-do"
        ));

        taskList.setValue(tasks);
    }
}
