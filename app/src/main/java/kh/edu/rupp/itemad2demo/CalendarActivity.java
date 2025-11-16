package kh.edu.rupp.itemad2demo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kh.edu.rupp.itemad2demo.views.CalendarAdapter;

public class CalendarActivity extends AppCompatActivity {

    TextView textMonthTitle;
    RecyclerView recyclerCalendar;

    Calendar calendar = Calendar.getInstance();
    CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initViews();
        updateCalendar();
        setupButtons();
    }

    private void initViews() {
        textMonthTitle = findViewById(R.id.textMonthTitle);
        recyclerCalendar = findViewById(R.id.recyclerCalendar);
        recyclerCalendar.setLayoutManager(new GridLayoutManager(this, 7));
    }

    private void setupButtons() {
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnPrevMonth).setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        findViewById(R.id.btnNextMonth).setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });
    }

    private void updateCalendar() {

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        textMonthTitle.setText(sdf.format(calendar.getTime()));

        List<Integer> days = new ArrayList<>();

        Calendar temp = (Calendar) calendar.clone();
        temp.set(Calendar.DAY_OF_MONTH, 1);

        int firstDay = temp.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDays = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < firstDay; i++) {
            days.add(0);
        }

        for (int d = 1; d <= maxDays; d++) {
            days.add(d);
        }

        adapter = new CalendarAdapter(this, days);
        recyclerCalendar.setAdapter(adapter);
    }
}
