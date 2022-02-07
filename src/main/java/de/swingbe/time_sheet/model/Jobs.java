package de.swingbe.time_sheet.model;

import java.util.HashMap;
import java.util.Map;

public class Jobs {

    private final Map<String, Job> jobs;

    public Jobs() {
        this.jobs = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobs=" + jobs +
                '}';
    }

    public Map<String, Job> getJobs() {
        return jobs;
    }

    public int size() {
        return jobs.size();
    }

    public void clear() {
        jobs.clear();
    }

    public void put(Job job) {
        jobs.put(job.getName(), job);
    }

    public Job get(String key) {
        return jobs.get(key);
    }

}
