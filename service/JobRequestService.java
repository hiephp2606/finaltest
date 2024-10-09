package service;

import database.AccountData;
import database.JobData;
import database.JobRequestData;
import entities.Account;
import entities.Job;
import entities.JobRequest;

import java.util.Scanner;

public class JobRequestService {
    LoginService loginService;
    JobPostService jobPostService;
    Scanner scanner = new Scanner(System.in);

    public JobRequestService(LoginService loginService, JobPostService jobPostService) {
        this.loginService = loginService;
        this.jobPostService = jobPostService;
    }

//    forFinder

    public void applyJob (Job job) {
        System.out.print("Nhap cong viec ban muon dang ky: ");
        int jobSelect = Integer.parseInt(scanner.nextLine());
        JobRequest jobRequest = new JobRequest(loginService.who.getId(), jobSelect, JobRequest.Status.PENDING, "null");
        JobRequestData.saveJobRequest(jobRequest);
        System.out.println("Yeu cau cua ban dang duoc duyet");
    }

    public void listJobRequestFinder () {
        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getFinderId() == loginService.who.getId()) {
                System.out.println(jobRequest.printBrief());
            }
        }
    }


//    forPoster
    public void listJobRequestPoster () {
        for (Job job : JobData.getJobList()) {
            if (job.getPosterId() == loginService.who.getId()) {
                for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
                    if (jobRequest.getJobId() == job.getId()) {
                        System.out.println(jobRequest.printDetail());
                    }
                }
            }
        }
    }

    public void replyJobRequest () {
        System.out.println("Nhap Id cong viec ban muon duyet: ");
        int choiceReply = Integer.parseInt(scanner.nextLine());
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
        Account account = AccountData.getAccountById(jobRequest.getFinderId());

    }

    public void approveJobRequest (JobRequest jobRequest) {
        System.out.print("Nhap ket qua duyet [Chap nhan/ Tu choi/ Thoat]: ");
        String reply = scanner.nextLine();
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


}
