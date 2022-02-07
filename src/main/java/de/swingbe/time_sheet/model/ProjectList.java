package de.swingbe.time_sheet.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private final List<Project> projectList;

    public ProjectList() {
        this.projectList = new ArrayList<>();
    }

    public int size() {
        return projectList.size();
    }

    public void clear() {
        projectList.clear();
    }

    public void add(Project project) {
        projectList.add(project);
    }

    public Project get(int index) {
        return projectList.get(index);
    }

    public List<Project> getTimeSheet() {
        return projectList;
    }
}
