package service.byrole;

import database.AccountData;
import database.JobData;
import database.JobRequestData;
import entities.Account;
import entities.Job;
import entities.JobRequest;
import service.common.LoginService;
import service.common.RegisterService;
import service.intf.*;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinderService implements ViewPost, FilterPost, ApplyJob, ViewJobRequest, ViewAcceptedJob, ViewRejectedJob, DeleteJobRequest, UpdateEmail, UpdatePassword, UpdatePhonenumber {
    LoginService loginService;
    List<Job> displayedList;
    Scanner scanner;
    List<JobRequest> lJobRequestFinder = new ArrayList<>();
    List<JobRequest> lAcceptedJobRequest = new ArrayList<>();
    List<JobRequest> lRejectedJobRequest = new ArrayList<>();
    RegisterService registerService = new RegisterService();

    public FinderService(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
        this.displayedList = JobData.getJobList();
    }

//    listJob
    public void listJob () {
        displayedList = new ArrayList<>();
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.ACTIVE)) {
                displayedList.add(job);
            }
        }
        if (displayedList.isEmpty()) {
            System.out.println("Chua co cong viec nao");
        }
        else {
            for (Job job : displayedList) {
                System.out.println(job.printBrief());
            }
        }
    }


//    Dang ky cong viec
    public void applyJobService () {
        System.out.print("Nhap cong viec ban muon dang ky -> ");
        int jobSelect = InputUtils.loopInputInteger("Nhap ID: ", scanner);
        JobRequest jobRequest = new JobRequest(loginService.who.getId(), jobSelect, JobRequest.Status.PENDING, "null");
        jobRequest.setCV(InputUtils.loopInputPath("Nhap duong dan den File CV: ", scanner));
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
                lAcceptedJobRequest.add(jobRequest);
                System.out.println(jobRequest.printBrief());
            }
        }
    }

    public void listRejectedJobRequest () {

        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getFinderId() == loginService.who.getId() && jobRequest.getStatus().equals(JobRequest.Status.REJECT)) {
                lRejectedJobRequest.add(jobRequest);
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
            int choiceDelete = InputUtils.loopInputInteger("Nhap cong id cong viec ban muon xoa: " ,scanner);
            listJobRequestFinder();

            for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
                if (jobRequest.getId() == choiceDelete) {
                    JobRequestData.removeJobRequest(jobRequest);
                }
            }
        }
    }

//    findFilter
    public void findJobByTitle() {
        String jobNeedle = InputUtils.loopInputString("Nhap cong viec ban muon tim: ", scanner);
        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getJobTitle().toLowerCase().contains(jobNeedle.toLowerCase())) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterJobByWorkplace () {
        int WPchoice = InputUtils.loopInputChoice(
                List.of("Loc theo noi lam viec:",
                "\t1. Lam o nha",
                "\t2. Lam tai tru so"),
                scanner,
                1,
                2);
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
        int WTchoice = InputUtils.loopInputChoice(
                List.of("Loc theo thoi gian lam viec:",
                        "\t1. Full time",
                        "\t2. Part time"),
                scanner,
                1,
                2);

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
        int minSalary = InputUtils.loopInputInteger("\tMuc luong toi thieu: ", scanner);
        int maxSalary = InputUtils.loopInputInteger("\tMuc luong toi da: ", scanner);

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
        int choice = InputUtils.loopInputInteger("Nhap cong viec ban muon xem: ", scanner);
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
        findJobByTitle();
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

    @Override
    public void updateEmail() {
        do {
            String email = InputUtils.loopInputEmail("Cap nhat email: ", scanner);
                if (registerService.checkEmail(email) != null) {

                }
                else {
                    AccountData.getAccountById(loginService.who.getId()).setEmail(email);
                    break;
                }
        } while (true);
    }

    @Override
    public void updatePassword() {
        String password = InputUtils.loopInputPassword("Cap nhat password: ", scanner);
        AccountData.getAccountById(loginService.who.getId()).setPassword(password);
    }

    @Override
    public void updatePhonenumber() {
        do {
            int phoneNumber = InputUtils.loopInputPhoneNumber("Cap nhat so dien thoai: ", scanner);
                if (registerService.checkPhoneNumber(phoneNumber) != null) {

                }
                else {
                    AccountData.getAccountById(loginService.who.getId()).setPhoneNumber(phoneNumber);
                    break;
                }
        } while (true);
    }
}
