package kh.edu.rupp.itemad2demo.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.itemad2demo.R;
import kh.edu.rupp.itemad2demo.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.textProject.setText(task.getProjectName());
        holder.textTaskName.setText(task.getTaskName());
        holder.textTime.setText(task.getTime());
        holder.textStatus.setText(task.getStatus());

        // Apply correct status background + color
        switch (task.getStatus()) {

            case "Done":
                holder.textStatus.setBackgroundResource(R.drawable.bg_status_done);
                holder.textStatus.setTextColor(0xFF6A4CFF);
                break;

            case "In Progress":
                holder.textStatus.setBackgroundResource(R.drawable.bg_status_progress);
                holder.textStatus.setTextColor(0xFFEA8C3D);
                break;

            default:  // To-do
                holder.textStatus.setBackgroundResource(R.drawable.bg_status_todo);
                holder.textStatus.setTextColor(0xFF4A90E2);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // Update list dynamically
    public void updateTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView textProject, textTaskName, textTime, textStatus;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            textProject = itemView.findViewById(R.id.textProject);
            textTaskName = itemView.findViewById(R.id.textTaskName);
            textTime = itemView.findViewById(R.id.textTime);
            textStatus = itemView.findViewById(R.id.textStatus);
        }
    }
}
