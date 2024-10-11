package service.byrole;

import database.JobData;
import database.JobRequestData;
import entities.Account;
import entities.Job;
import entities.JobRequest;
import service.JobService;
import service.LoginService;
import service.intf.*;
import ultis.Ultis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinderService implements ViewPost, FilterPost, ApplyJob, ViewJobRequest, ViewAcceptedJob, ViewRejectedJob, DeleteJobRequest {
    LoginService loginService;
    List<Job> displayedList = new ArrayList<>();
    JobService jobService = new JobService();
    Scanner scanner;
    List<JobRequest> lJobRequestFinder = new ArrayList<>();
    List<JobRequest> lAcceptedJobRequest = new ArrayList<>();
    List<JobRequest> lRejectedJobRequest = new ArrayList<>();

    public FinderService(LoginService loginService, List<Job> displayedList, JobService jobService, Scanner scanner) {
        this.loginService = loginService;
        this.displayedList = displayedList;
        this.jobService = jobService;
        this.scanner = scanner;
    }

//    listJob
    public void listJob () {
        if (JobData.getJobList().size() == 0) {
            System.out.println("Chua co cong viec nao");
        }
        else {
            for (Job job : JobData.getJobList()) {
                System.out.print(job.printBrief());
            }
        }
    }


//    Dang ky cong viec
    public void applyJobService () {
        System.out.print("Nhap cong viec ban muon dang ky: ");
        int jobSelect = Ultis.inputInteger(scanner);
        JobRequest jobRequest = new JobRequest(loginService.who.getId(), jobSelect, JobRequest.Status.PENDING, "null");
        JobRequestData.saveJobRequest(jobRequest);
        System.out.println("Yeu cau cua ban dang duoc duyet");
    }

//    Xem cong viec da dang ky
    public void listJobRequestFinder () {
        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getFinderId() == loginService.who.getId() && jobRequest.getStatus().equals(JobRequest.Status.PENDING)) {
                lJobRequestFinder.add(jobRequest);
                System.out.println(jobRequest.printBrief());
            }
        }
    }

    public void listAcceptedJobRequest () {
        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getFinderId() == loginService.who.getId() && jobRequest.getStatus().equals(JobRequest.Status.ACCEPT)) {
                System.out.println(jobRequest.printBrief());
            }
        }
    }

    public void listRejectedJobRequest () {

        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getFinderId() == loginService.who.getId() && jobRequest.getStatus().equals(JobRequest.Status.REJECT)) {
                System.out.println(jobRequest.printBrief());
            }
        }
    }

//    Huy dang ky cong viec
    public void deleteAppliedJob () {
        if (lJobRequestFinder.isEmpty()) {
            System.out.println("khong co cong viec nao kha dung");
        } else {
            listJobRequestFinder();
            System.out.print("Nhap cong id cong viec ban muon xoa: ");
            int choiceDelete = Ultis.inputInteger(scanner);
            listJobRequestFinder();

            for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
                if (jobRequest.getId() == choiceDelete) {
                    JobRequestData.removeJobRequest(jobRequest);
                }
            }
        }
    }

//    findFilter
    public void findJob () {
        System.out.print("Nhap cong viec ban muon tim: ");
        String jobNeedle = Ultis.inputString(scanner);
        for (Job job : JobData.getJobList()) {
            if (job.getJobTitle().contains(jobNeedle)) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterJobByWorkplace () {
        System.out.println("Loc theo noi lam viec:");
        System.out.println("\t1. Lam o nha");
        System.out.println("\t2. Lam tai tru so");
        int WPchoice = Ultis.inputInteger(scanner);

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getPlaceType().ordinal() == WPchoice) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterJobByWorkTime () {
        System.out.println("Loc theo thoi gian lam viec:");
        System.out.println("\t1. Full time");
        System.out.println("\t2. Part time");
        int WTchoice = Ultis.inputInteger(scanner);

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getTimeType().ordinal() == WTchoice) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterSalary () {
        System.out.println("Loc theo muc luong:");
        System.out.print("\tMuc luong toi thieu: ");
        int minSalary = Ultis.inputInteger(scanner);
        System.out.print("\tMuc luong toi da: ");
        int maxSalary = Ultis.inputInteger(scanner);

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getSalary() >= minSalary && job.getSalary() <= maxSalary) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    private void printFilteredJob() {
        for (Job job : displayedList) {
            System.out.println(job.printBrief());
        }
    }

//    selectJob
    public void selectJob () {
        System.out.print("Nhap cong viec ban muon xem: ");
        int choice = Ultis.inputInteger(scanner);
        Job job = displayedList.get(choice);
        System.out.println(job.printDetail());
    }


    @Override
    public void getPost() {
        listJob();
    }

    @Override
    public void filterByTitle() {
        printFilteredJob();
        findJob();
    }

    @Override
    public void filterBySalary() {
        printFilteredJob();
        filterByTitle();
    }

    @Override
    public void filterByWorkPlace() {
        printFilteredJob();
        filterJobByWorkplace();
    }

    @Override
    public void filterByWorkTime() {
        printFilteredJob();
        filterJobByWorkTime();
    }


    @Override
    public void applyJob() {
        if (JobData.getJobList().size() == 0) {
            System.out.println("Chua co cong viec de dang ky");
        }
        else {
            applyJobService();
        }
    }

    @Override
    public void getJobRequest() {
        listJobRequestFinder();
        if (lJobRequestFinder.isEmpty()) {
            System.out.println("Ban chua dang ky them cong viec nao");
        }
    }

    @Override
    public void acceptedJob() {
        listAcceptedJobRequest();
        if (lAcceptedJobRequest.isEmpty()) {
            System.out.println("Ban chua co them thong bao cong viec nao");
        }
    }

    @Override
    public void rejectedJob() {
        listRejectedJobRequest();
        if(lRejectedJobRequest.isEmpty()) {
            System.out.println("Chua co cong viec nao de hien thi");
        }
    }

    @Override
    public void removeJobRequest() {
        deleteAppliedJob();
    }
}
