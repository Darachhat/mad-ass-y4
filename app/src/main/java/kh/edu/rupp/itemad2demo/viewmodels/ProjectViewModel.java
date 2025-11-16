package kh.edu.rupp.itemad2demo.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Project;

public class ProjectViewModel extends ViewModel {

    private final MutableLiveData<List<Project>> projects = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<Project>> getProjects() {
        return projects;
    }

    public void addProject(String name, String desc, String group, String start, String end) {
        List<Project> list = projects.getValue();

        list.add(new Project(name, desc, group, start, end));
        projects.setValue(list);
    }
}
