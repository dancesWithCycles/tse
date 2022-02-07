package de.swingbe.time_sheet.model;

public class TimeSheetEntry {
    private final String status;
    private final String date;
    private final String job;
    private final String project;
    private final String startTime;
    private final String endTime;
    private final String description;

    public TimeSheetEntry(String status, String date, String job, String project, String startTime, String endTime, String description) {
        this.status = status;
        this.date = date;
        this.job = job;
        this.project = project;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "TimeSheetEntry{" +
                "status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", job='" + job + '\'' +
                ", project='" + project + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getProject() {
        return project;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }
}
