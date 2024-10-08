package service;

import database.JobRequestData;
import entities.JobRequest;

import java.util.Scanner;

public class FinderService {
    LoginService loginService;
    JobPostService jobPostService;
    Scanner scanner = new Scanner(System.in);

    public FinderService(LoginService loginService, JobPostService jobPostService) {
        this.loginService = loginService;
        this.jobPostService = jobPostService;
    }

    public void applyJob () {
        System.out.print("Nhap cong viec ban muon dang ky: ");
        int jobSelect = Integer.parseInt(scanner.nextLine());
        JobRequest jobRequest = new JobRequest(loginService.who.getId(), jobSelect, JobRequest.Status.PENDING, "null");
        JobRequestData.saveJobRequest(jobRequest);
        System.out.println("Yeu cau cua ban dang duoc duyet");
    }

    public void listJobRequest () {
        for (JobRequest jobRequest : JobRequestData.getJobRequestList()) {
            if (jobRequest.getUserId() == loginService.who.getId()) {
                System.out.println(jobRequest.printBrief());
            }
        }
    }

}
