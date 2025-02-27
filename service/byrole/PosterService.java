package service.byrole;

import database.AccountData;
import database.JobData;
import database.JobRequestData;
import entities.Account;
import entities.Job;
import entities.JobRequest;
import service.common.AccountService;
import service.common.LoginService;
import service.common.RegisterService;
import service.intf.*;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PosterService implements CreatePost, ViewPost, DeletePost, ViewJobRequest, ApproveJobRequest, UpdatePassword, UpdateEmail, UpdatePhonenumber {
    LoginService loginService;
    Scanner scanner;
    List<JobRequest> jobRequests = new ArrayList<>();
    RegisterService registerService = new RegisterService();

    public PosterService(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    private Job createJob(Account account) {
        String jobTitle = InputUtils.loopInputString("Nhap thong tin co ban cua cong viec cua ban: ", scanner);
        String jobDescribe = InputUtils.loopInputString("Mo ta cong viec: ", scanner);
        int employeeNumber = InputUtils.loopInputInteger("Nhap so luong nhan vien ban muon tuyen: ", scanner);
        int salary = InputUtils.loopInputInteger("Nhap luong cua cong viec theo thang: ", scanner);
        Job.WorkPlaceType workPlaceType = typeChoice();
        Job.TimeType timeType = timeChoice();
        Job job = new Job(account.getId(), workPlaceType, timeType, jobTitle, jobDescribe, employeeNumber, salary, Job.Status.AVAILABLE, Job.JobStatus.INACTIVE);
        JobData.saveJob(job);
        System.out.println("Tao cong viec thanh cong!");
        return job;
    }

    //    typeService
    private Job.WorkPlaceType typeChoice() {
        int choiceWP = InputUtils.loopInputChoice(
                List.of(
                        "Loc theo noi lam viec:",
                        "\t1. Lam o nha",
                        "\t2. Lam tai tru so"
                ),
                scanner,
                1,
                2
        );
        switch (choiceWP) {
            case 1:
                return Job.WorkPlaceType.WFH;
            default:
                return Job.WorkPlaceType.OS;
        }
    }

    private Job.TimeType timeChoice() {
        int choiceWT = InputUtils.loopInputChoice(
                List.of(
                        "Loc theo thoi gian lam viec:",
                        "\t1. Full time",
                        "\t2. Part time"
                ),
                scanner,
                1,
                2
        );
        switch (choiceWT) {
            default:
                return Job.TimeType.FT;
            case 2:
                return Job.TimeType.PT;
        }
    }

    public void replyJobRequest () {
        System.out.println("Nhap Id cong viec ban muon duyet: ");
        int choiceReply = InputUtils.loopInputInteger("", scanner);
        JobRequest jobRequest = JobRequestData.getJobRequestById(choiceReply);
        System.out.println("Hay nhap lua chon:");
        System.out.println("\t 1. Xem CV");
        System.out.println("\t 2. Phe duyet");

        int choiceRequest = Integer.parseInt(scanner.nextLine());
        switch (choiceRequest) {
            case 1:
                System.out.println(jobRequest.getCV());
                break;
            case 2:
                approveJobRequest(jobRequest);
                break;
        }
    }

    public void approveJobRequest (JobRequest jobRequest) {
        System.out.print("Nhap ket qua duyet [Chap nhan/ Tu choi/ Thoat]: ");
        String reply = InputUtils.loopInputString("", scanner);
        if (reply.equalsIgnoreCase("Chap nhan")) {
            jobRequest.setStatus(JobRequest.Status.ACCEPT);
        }
        else if (reply.equalsIgnoreCase("Tu choi")) {
            jobRequest.setStatus(JobRequest.Status.REJECT);
        }
        else if (reply.equalsIgnoreCase("Thoat")) {
        }
        else {
            System.out.println("Khong co lua chon nay!");
        }
    }

    @Override
    public void createPost() {
        createJob(loginService.who);
    }

    @Override
    public void getPost() {
        int jobNumber = 0;
        for (Job job : JobData.getJobList()) {
            if (loginService.who.getId() == job.getPosterId()) {
                jobNumber = jobNumber + 1;
            }
        }
        if (jobNumber == 0) {
            System.out.println("Ban chua tao cong viec nao gan day");
        }
        else {
            for (Job job : JobData.getJobList()) {
                if (loginService.who.getId() == job.getPosterId()) {
                    System.out.println(job.printBrief());
                }
            }
        }
    }

    //    listJobRequest
    public void jobRequestsList () {
        for (Job job : JobData.getJobList()) {
            if (job.getPosterId() == loginService.who.getId()) {
                for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
                    if (jobRequest.getJobId() == job.getId()) {
                        System.out.println(jobRequest.printDetail());
                        jobRequests.add(jobRequest);
                    }
                }
            }
        }
    }

    @Override
    public void getJobRequest() {
        jobRequestsList();
        if (jobRequests.isEmpty()) {
            System.out.println("Chua co ai dang ky them!");
        }
    }

    @Override
    public void deletePost() {
        jobRequestsList();
        if (!jobRequests.isEmpty()) {
            int id =InputUtils.loopInputInteger("Nhap ID: ", scanner);
            if (JobData.getJobById(id).getPosterId() == loginService.who.getId()) {
                JobData.removeJob(id);
            }
        } else {
            System.out.println("Khong co cong viec nao kha dung");
        }
    }

    @Override
    public void approveJobRequest() {
        jobRequestsList();
        if (!jobRequests.isEmpty()) {
            replyJobRequest();
        } else {
            System.out.println("Khong co cong viec nao kha dung");
        }
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
