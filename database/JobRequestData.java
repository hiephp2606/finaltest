package database;

import entities.Job;
import entities.JobRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobRequestData {
    public static Map<Integer, JobRequest> jobRequestMap = new HashMap<>();

    public static List<JobRequest> getJobRequestList() {
        return new ArrayList<>(jobRequestMap.values());
    }

    public static void saveJobRequest (JobRequest jobRequest) {
        jobRequestMap.put(jobRequest.getId(), jobRequest);
    }

    public static JobRequest getJobRequestById (int id) {
        return jobRequestMap.get(id);
    }

    public static JobRequest removeJobRequest (JobRequest jobRequest) {
        return jobRequestMap.get(jobRequest.getId());
    }

    public static String getCVById (int id) {
        return jobRequestMap.get(id).getCV();
    }
}
