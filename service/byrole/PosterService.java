package service.byrole;

import database.JobData;
import database.JobRequestData;
import entities.Account;
import entities.Job;
import entities.JobRequest;
import service.LoginService;
import service.intf.*;
import ultis.Ultis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PosterService implements CreatePost, ViewPost, DeletePost, ViewJobRequest, ApproveJobRequest {
    LoginService loginService;
    Scanner scanner;
    List<JobRequest> jobRequests = new ArrayList<>();

    public PosterService(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    private Job createJob(Account account) {
        System.out.print("Nhap thong tin co ban cua cong viec cua ban: ");
        String jobTitle = Ultis.inputString(scanner);
        System.out.print("Mo ta cong viec: ");
        String jobDescribe = Ultis.inputString(scanner);
        System.out.print("Nhap so luong nhan vien ban muon tuyen: ");
        int employeeNumber = Ultis.inputInteger(scanner);
        System.out.print("Nhap luong cua cong viec theo thang: ");
        int salary = Ultis.inputInteger(scanner);
        Job.WorkPlaceType workPlaceType = typeChoice();
        Job.TimeType timeType = timeChoice();
        Job job = new Job(account.getId(), workPlaceType, timeType, jobTitle, jobDescribe, employeeNumber, salary, Job.Status.AVAILABLE, Job.JobStatus.INACTIVE);
        JobData.saveJob(job);
        System.out.println("Tao cong viec thanh cong!");
        return job;
    }

    //    typeService
    private Job.WorkPlaceType typeChoice() {
        System.out.print("Chon noi lam viec: \n  1. Lam o nha\n  2. Lam ben ngoai");
        int choiceWP = Ultis.inputInteger(scanner);
        switch (choiceWP) {
            case 1:
                return Job.WorkPlaceType.WFH;
            case 2:
                return Job.WorkPlaceType.OS;
            default:
                System.out.println("Cap nhat sau!");
                return Job.WorkPlaceType.NULL;
        }
    }

    private Job.TimeType timeChoice() {
        System.out.print("Chon tinh chat thoi gian cong viec: \n  1. full time\n  2. part time");
        int choiceWT = Ultis.inputInteger(scanner);
        switch (choiceWT) {
            case 1:
                return Job.TimeType.FT;
            case 2:
                return Job.TimeType.PT;
            default:
                System.out.println("Cap nhat sau!");
                return Job.TimeType.NULL;
        }
    }

    public void replyJobRequest () {
        System.out.println("Nhap Id cong viec ban muon duyet: ");
        int choiceReply = Ultis.inputInteger(scanner);
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
        String reply = Ultis.inputString(scanner);
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
                    System.out.print(job.printBrief());
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
    }

    @Override
    public void deletePost() {
        jobRequestsList();
        if (!jobRequests.isEmpty()) {
            int id = Ultis.inputId(scanner);
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
}
