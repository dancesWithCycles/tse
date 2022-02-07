package de.swingbe.time_sheet.model;

import java.util.Date;

public class Project {
    private final String name;
    private final Date start;
    private final Date end;
    private final double hours;

    public Project(String name, Date start, Date end, double hours) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", hours=" + hours +
                '}';
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public double getHours() {
        return hours;
    }
}
