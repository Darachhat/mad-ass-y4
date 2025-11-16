package kh.edu.rupp.itemad2demo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Task;

public class TaskViewModelBak extends ViewModel {
    private MutableLiveData<String> inputTitle = new MutableLiveData<>("");
    private MutableLiveData<String> taskTitle = new MutableLiveData<>("");

    public MutableLiveData<String> getInputTitle() {
        return inputTitle;
    }

    public void setInputTitle(MutableLiveData<String> inputTitle) {
        this.inputTitle = inputTitle;
    }

    private MutableLiveData<List<Task>> taskLiveData = new MutableLiveData<>(new ArrayList<>());
    public void addTask(String title){
        List<Task> currentTasks = taskLiveData.getValue();
        currentTasks.add(new Task(title));
        taskLiveData.setValue(currentTasks);
    }
    public LiveData<List<Task>> getTasks(){
        return taskLiveData;
    }
}
