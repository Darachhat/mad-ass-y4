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
import kh.edu.rupp.itemad2demo.models.Project;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private Context context;
    private List<Project> projectList;

    public ProjectAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project p = projectList.get(position);

        holder.textProjectName.setText(p.getName());
        holder.textProjectGroup.setText("Group: " + p.getGroup());
        holder.textProjectDates.setText(p.getStartDate() + " → " + p.getEndDate());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public void updateProjects(List<Project> projects) {
        this.projectList = projects;
        notifyDataSetChanged();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView textProjectName, textProjectGroup, textProjectDates;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            textProjectName = itemView.findViewById(R.id.textProjectName);
            textProjectGroup = itemView.findViewById(R.id.textProjectGroup);
            textProjectDates = itemView.findViewById(R.id.textProjectDates);
        }
    }
}
