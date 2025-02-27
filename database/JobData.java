package database;

import entities.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobData {
    public static Map<Integer, Job> jobMap = new HashMap<>();

    static {
        Job chayquan = new Job(3, Job.WorkPlaceType.OS, Job.TimeType.PT, "Phuc vu cafe", "Vui", 2, 1000, Job.Status.AVAILABLE, Job.JobStatus.ACTIVE);
        Job laptrinhvien = new Job(4, Job.WorkPlaceType.WFH, Job.TimeType.FT, "code java", "Vui", 3, 10000, Job.Status.AVAILABLE, Job.JobStatus.INACTIVE);
        jobMap.put(chayquan.getId(), chayquan);
        jobMap.put(laptrinhvien.getId(), laptrinhvien);
    }

    //    saveJobData
    public static void saveJob(Job job) {
        jobMap.put(job.getId(), job);
    }

    public static Job getJobById(int id) {
        return jobMap.get(id);
    }

    public static List<Job> getJobList() {
        return new ArrayList<>(jobMap.values());
    }

    public static void removeJob(int id) {
        jobMap.remove(id);
    }
}
