package database;

import entities.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobData {
    public static Map<Integer, Job> jobMap = new HashMap<>();

//    saveJobData
    public static void saveJob (Job job) {
        jobMap.put(job.getId(), job);
    }

    public static Job getJobById(int id) {
        return jobMap.get(id);
    }

    public static List<Job> getJobList() {
        return new ArrayList<>(jobMap.values());
    }
}
