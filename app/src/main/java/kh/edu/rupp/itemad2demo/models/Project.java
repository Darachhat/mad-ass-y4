package kh.edu.rupp.itemad2demo.models;

public class Project {

    private String name;
    private String description;
    private String group;
    private String startDate;
    private String endDate;

    public Project(String name, String description, String group, String startDate, String endDate) {
        this.name = name;
        this.description = description;
        this.group = group;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getGroup() { return group; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
}
