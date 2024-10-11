package view;

import database.JobRequestData;
import entities.JobRequest;
import service.JobPostService;
import service.JobRequestService;
import service.JobService;
import service.LoginService;
import service.byrole.PosterService;

import java.util.Scanner;

public class PosterMenu {
    LoginService loginService;
    Scanner scanner;

    public PosterMenu(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    void mainMenu() {
        System.out.println("1. Xem cong viec da tao");
        System.out.println("2. Xem don ung tuyen");
        System.out.println("3. Them cong viec");
        System.out.println("4. Xoa cong viec");
        System.out.println("5. Phe duyet don");
    }

    void viewJob() {
        new PosterService(loginService, scanner).getPost();
    }

    void viewApplicant() {
        new PosterService(loginService, scanner).getJobRequest();
    }

    void createJob() {
        new PosterService(loginService, scanner).createPost();
    }

    void deleteJob() {
        new PosterService(loginService, scanner).deletePost();
    }

    void viewApproveJobRequest() {
        new PosterService(loginService, scanner).approveJobRequest();
    }
}
