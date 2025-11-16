package kh.edu.rupp.itemad2demo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Task;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<String> inputTitle = new MutableLiveData<>("");
    private MutableLiveData<String> taskTitle = new MutableLiveData<>("");
    public MutableLiveData<String> getInputTitle() {
        return inputTitle;
    }

    public void setInputTitle(MutableLiveData<String> inputTitle) {
        this.inputTitle = inputTitle;
    }
    public LiveData<String> getTask(){
        return taskTitle;
    }
    public List<Task> tasks = new ArrayList<>();
    public void onAddClicked(){
        String title = inputTitle.getValue().toString();
        if(title.isEmpty()) return;

        tasks.add(new Task(title));

        StringBuilder builder = new StringBuilder();
        for (Task t : tasks){
            builder.append(t.getText()).append("\n");
        }
        taskTitle.setValue(builder.toString());
    }
}
