package kh.edu.rupp.itemad2demo.models;

public class Task {

    private String title;
    private String subtitle;
    private String time;
    private String status;
    private String category;
    private int statusColor;

    public Task(String title,
                String subtitle,
                String time,
                String status,
                String category,
                int statusColor) {

        this.title = title;
        this.subtitle = subtitle;
        this.time = time;
        this.status = status;
        this.category = category;
        this.statusColor = statusColor;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    public String getCategory() { return category; }
    public int getStatusColor() { return statusColor; }

    public void setTitle(String title) { this.title = title; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public void setTime(String time) { this.time = time; }
    public void setStatus(String status) { this.status = status; }
    public void setCategory(String category) { this.category = category; }
    public void setStatusColor(int statusColor) { this.statusColor = statusColor; }
}
