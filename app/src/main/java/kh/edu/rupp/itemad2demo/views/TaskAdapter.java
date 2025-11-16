package kh.edu.rupp.itemad2demo.views;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.itemad2demo.R;
import kh.edu.rupp.itemad2demo.databinding.ItemTaskBinding;
import kh.edu.rupp.itemad2demo.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> items = new ArrayList<>();

    public void setItems(List<Task> newList) {
        items = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_task,
                parent,
                false
        );
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = items.get(position);

        holder.binding.textTitle.setText(task.getTitle());
        holder.binding.textSubtitle.setText(task.getSubtitle());
        holder.binding.textTime.setText(task.getTime());
        holder.binding.textStatus.setText(task.getStatus());

        // color status badge
        Drawable bg = holder.binding.textStatus.getBackground();
        if (bg != null) {
            Drawable wrapped = DrawableCompat.wrap(bg);
            DrawableCompat.setTint(wrapped, task.getStatusColor());
            holder.binding.textStatus.setBackground(wrapped);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;

        public TaskViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
