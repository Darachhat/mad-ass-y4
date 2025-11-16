package kh.edu.rupp.itemad2demo.models;

public class Task {

    private String projectName;
    private String taskName;
    private String time;
    private String status;

    public Task(String projectName, String taskName, String time, String status) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.time = time;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
