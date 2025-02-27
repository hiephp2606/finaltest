package database;

import entities.JobRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class JobRequestData {
    public static Map<Integer, JobRequest> jobRequestMap = new HashMap<>();

    public static List<JobRequest> getJobRequestList() {
        return new ArrayList<>(jobRequestMap.values());
    }

    public static void saveJobRequest (JobRequest jobRequest) {
        try {
            String localPath = "C:\\CV\\" + UUID.randomUUID();
            Files.copy(Path.of(jobRequest.getCV()), Path.of(localPath), StandardCopyOption.REPLACE_EXISTING);
            jobRequest.setCV(localPath);
            jobRequestMap.put(jobRequest.getId(), jobRequest);
        } catch (IOException e) {
            System.out.println("Tao that bai, file CV co van de!");
        }
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
