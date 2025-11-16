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

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DayViewHolder> {

    private Context context;
    private List<Integer> days;
    private int selectedPosition = -1;

    public CalendarAdapter(Context context, List<Integer> days) {
        this.context = context;
        this.days = days;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.calendar_day_item, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        int day = days.get(position);

        if (day == 0) {
            holder.textDay.setText("");
            holder.textDay.setBackgroundResource(R.drawable.bg_calendar_day);
            return;
        }

        holder.textDay.setText(String.valueOf(day));

        if (selectedPosition == position) {
            holder.textDay.setTextColor(0xFFFFFFFF);
            holder.textDay.setBackgroundResource(R.drawable.bg_calendar_day_selected);
        } else {
            holder.textDay.setTextColor(0xFF1C1E26);
            holder.textDay.setBackgroundResource(R.drawable.bg_calendar_day);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView textDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            textDay = itemView.findViewById(R.id.textDay);
        }
    }
}
