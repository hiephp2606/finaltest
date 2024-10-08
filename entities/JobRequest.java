package entities;

import database.JobData;
// for everyone
public class JobRequest {
    private int userId;
    private int jobId;
    private int id;
    private static int autoId;
    private Status status;
    private String CV;

    public enum Status {
        PENDING, ACCEPT, REJECT
    }

    public JobRequest(int userId, int jobId, Status status, String CV) {
        this.jobId = jobId;
        this.userId = userId;
        this.id = ++autoId;
        this.status = status;
        this.CV = CV;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        JobRequest.autoId = autoId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String printBrief() {
        String jobTitle = JobData.getJobById(getJobId()).getJobTitle();
        return String.join(" ",getId().toString() + ".", jobTitle, getStatus().toString());
    }
}
