package kh.edu.rupp.itemad2demo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.models.Task;

public class TaskViewModel extends ViewModel {
    // Two-way binding for the input field in layout
    private final MutableLiveData<String> inputTitle = new MutableLiveData<>("");

    // Expose task list as LiveData<List<Task>> for RecyclerView or other views
    private final MutableLiveData<List<Task>> taskLiveData = new MutableLiveData<>(new ArrayList<>());

    // --- InputTitle accessors (used by data binding) ---
    public MutableLiveData<String> getInputTitle() {
        return inputTitle;
    }

    public void setInputTitle(MutableLiveData<String> inputTitle) {
        // rarely used, but kept for completeness
        if (inputTitle != null) {
            this.inputTitle.setValue(inputTitle.getValue());
        }
    }

    // --- Tasks accessors ---
    public LiveData<List<Task>> getTasks() {
        return taskLiveData;
    }

    /**
     * Add a new Task using the full Task constructor (title, subtitle, time, status, category, color).
     * This method is defensive: it ignores null/empty titles and ensures the internal list is non-null.
     */
    public void addTask(String title) {
        if (title == null || title.trim().isEmpty()) return;

        List<Task> currentTasks = taskLiveData.getValue();
        if (currentTasks == null) currentTasks = new ArrayList<>();

        Task task = new Task(
                title,                   // title
                "New task created",      // subtitle
                "08:00 PM",              // time
                "To-do",                 // status
                "General",               // category
                0xFF6FA8FF               // status color (example ARGB int)
        );

        currentTasks.add(task);
        taskLiveData.setValue(currentTasks);
    }

    /**
     * Called by the "Add" button (data-binding). Reads the inputTitle, adds the task,
     * then clears the inputTitle so the field is reset.
     */
    public void onAddClicked() {
        String title = inputTitle.getValue() != null ? inputTitle.getValue().trim() : "";
        if (title.isEmpty()) return;

        addTask(title);

        // clear input after adding
        inputTitle.setValue("");
    }

    /**
     * Remove a task by index. Returns true if removal succeeded.
     */
    public boolean removeTask(int index) {
        List<Task> current = taskLiveData.getValue();
        if (current == null || index < 0 || index >= current.size()) return false;
        current.remove(index);
        taskLiveData.setValue(current);
        return true;
    }

    /**
     * Seed the list with demo items (useful for previews / first run).
     */
    public void seedDemo() {
        List<Task> demo = new ArrayList<>();
        demo.add(new Task("Buy groceries", "Milk, eggs, bread", "06:30 PM", "To-do", "Shopping", 0xFFf39c12));
        demo.add(new Task("Call Sam", "Discuss project", "09:00 AM", "Done", "Work", 0xFF2ecc71));
        demo.add(new Task("Gym", "Leg day", "07:00 PM", "To-do", "Health", 0xFF3498db));
        taskLiveData.setValue(demo);
    }

    /**
     * Replace full list (helper, e.g., when loading from storage).
     */
    public void setTasks(List<Task> tasks) {
        if (tasks == null) tasks = new ArrayList<>();
        taskLiveData.setValue(tasks);
    }
}
