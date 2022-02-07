package de.swingbe.time_sheet.model;

import java.util.HashMap;
import java.util.Map;

public class Projects {
    private final Map<String, Project> projects;

    public Projects() {
        this.projects = new HashMap<>();
    }

    public int size() {
        return projects.size();
    }

    public void clear() {
        projects.clear();
    }

    public void put(Project project) {
        projects.put(project.getName(), project);
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projects=" + projects +
                '}';
    }

    public Project get(String key) {
        return projects.get(key);
    }

    public Map<String, Project> getProjects() {
        return projects;
    }
}
