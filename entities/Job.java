package entities;

import java.util.Arrays;
import java.util.List;

public class Job {
    private int userId;
    private int id;
    private static int autoId;
    private WorkPlaceType placeType;
    private TimeType timeType;
    private String jobTitle;
    private String jobDescribe;
    private int employeeNumber;
    private int salary;
    private Status status;

    public enum WorkPlaceType {
        NULL, WFH, OS
    }

    public enum TimeType {
        NULL, FT, PT
    }

    public enum Status {
        AVAILABLE, UNAVAILABLE
    }

    public Job(int userId ,WorkPlaceType placeType, TimeType timeType, String jobTitle, String jobDescribe, int employeeNumber, int salary, Status status) {
        this.userId = userId;
        this.id = ++autoId;
        this.placeType = placeType;
        this.timeType = timeType;
        this.jobTitle = jobTitle;
        this.jobDescribe = jobDescribe;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String printDetail() {
        return String.join("\n", getJobTitle(), getStatus().toString(), getSalary().toString(), getEmployeeNumber().toString(), getPlaceType().toString(), getTimeType().toString(), getJobDescribe());
    }

    public String printBrief() {
        return String.join(" ",getId().toString() + ".", getJobTitle(), getStatus().toString(), getPlaceType().toString(), getTimeType().toString());
    }
}
