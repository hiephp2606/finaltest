package view;

import entities.Job;
import service.JobService;
import service.LoginService;
import service.byrole.FinderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinderMenu {
    LoginService loginService;
    List<Job> displayedList = new ArrayList<>();
    JobService jobService = new JobService();
    Scanner scanner;

    public FinderMenu(LoginService loginService, List<Job> displayedList, JobService jobService, Scanner scanner) {
        this.loginService = loginService;
        this.displayedList = displayedList;
        this.jobService = jobService;
        this.scanner = scanner;
    }

    void mainMenu() {
        System.out.println("1. Xem danh sach cong viec");
        System.out.println("2. Tim cong viec");
        System.out.println("3. Xem cong viec da dang ky");
        System.out.println("4. Xem cong viec da duoc nhan");
        System.out.println("5. Xem cong viec bi tu choi");
        System.out.println("6. Huy dang ky cong viec");
    }

    void listJob() {
        new FinderService(loginService, displayedList, jobService, scanner).getPost();
    }

    void findJob() {
        new FinderService(loginService, displayedList, jobService, scanner).filterByTitle();
        new FinderService(loginService, displayedList, jobService, scanner).filterBySalary();
        new FinderService(loginService, displayedList, jobService, scanner).filterByWorkPlace();
        new FinderService(loginService, displayedList, jobService, scanner).filterByWorkTime();
        new FinderService(loginService, displayedList, jobService, scanner).applyJob();
    }

    void listAplliedJob () {
        new FinderService(loginService, displayedList, jobService, scanner).getJobRequest();
    }

    void listAccpetedJob () {
        new FinderService(loginService, displayedList, jobService, scanner).acceptedJob();
    }

    void listRejectedJob () {
        new FinderService(loginService, displayedList, jobService, scanner).rejectedJob();
    }

    void removeRequestJob () {
        new FinderService(loginService, displayedList, jobService, scanner).removeJobRequest();
    }
}
