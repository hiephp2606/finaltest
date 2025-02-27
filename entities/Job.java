package entities;

public class Job {
    private int PosterId;
    private int id;
    private static int autoId;
    private WorkPlaceType placeType;
    private TimeType timeType;
    private String jobTitle;
    private String jobDescribe;
    private int employeeNumber;
    private int salary;
    private Status status;
    private JobStatus jobStatus;

    public enum WorkPlaceType {
        NULL, WFH, OS
    }

    public enum TimeType {
        NULL, FT, PT
    }

    public enum Status {
        AVAILABLE, UNAVAILABLE
    }

    public enum JobStatus {
        ACTIVE, INACTIVE, DECLINE
    }

    public Job(int PosterId ,WorkPlaceType placeType, TimeType timeType, String jobTitle, String jobDescribe, int employeeNumber, int salary, Status status, JobStatus jobStatus) {
        this.PosterId = PosterId;
        this.id = ++autoId;
        this.placeType = placeType;
        this.timeType = timeType;
        this.jobTitle = jobTitle;
        this.jobDescribe = jobDescribe;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.status = status;
        this.jobStatus = jobStatus;
    }

    public int getPosterId() {
        return PosterId;
    }

    public void setPosterId(int posterId) {
        PosterId = posterId;
    }

    public WorkPlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(WorkPlaceType placeType) {
        this.placeType = placeType;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String printDetail() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(getId().toString()).append("\n");
        builder.append("\tTieu de: ").append(getJobTitle()).append("\n");
        builder.append("\tTrang thai:").append(getStatus().toString()).append("\n");
        builder.append("\tLuong: ").append(getSalary().toString()).append("\n");
        builder.append("\tSo ung vien: ").append(getEmployeeNumber().toString()).append("\n");
        builder.append("\tDia diem: ").append(getPlaceType().toString()).append("\n");
        builder.append("\tThoi gian: ").append(getTimeType().toString()).append("\n");
        builder.append("\tMo ta: ").append(getJobDescribe()).append("\n");
        return builder.toString();
    }

    public String printBrief() {
        return String.join(" ",getId().toString() + ".", getJobTitle(), getStatus().toString(), getPlaceType().toString(), getTimeType().toString());
    }
}
