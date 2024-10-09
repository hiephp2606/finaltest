package entities;

import database.AccountData;
import database.JobData;
// for everyone
public class JobRequest {
    private int finderId;
    private int jobId;
    private int id;
    private static int autoId;
    private Status status;
    private String CV;

    public enum Status {
        PENDING, ACCEPT, REJECT
    }

    public JobRequest(int finderId, int jobId, Status status, String CV) {
        this.jobId = jobId;
        this.finderId = finderId;
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

    public int getFinderId() {
        return finderId;
    }

    public void setFinderId(int finderId) {
        this.finderId = finderId;
    }


    public String printBrief() {
        String jobTitle = JobData.getJobById(getJobId()).getJobTitle();
        return String.join(" ",getId().toString() + ".", jobTitle, getStatus().toString());
    }

    public String printDetail() {
        String finderName = AccountData.getAccountById(getFinderId()).getName();
        String jobTitle = JobData.getJobById(getJobId()).getJobTitle();
        return String.join(" ",getId().toString() + ".",finderName, "-",  jobTitle);
    }
}
