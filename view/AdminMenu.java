package view;

import entities.Account;
import entities.Job;
import service.LoginService;
import service.byrole.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);
    List<Account> inactiveAccount = new ArrayList<>();
    List<Account> activeAccount = new ArrayList<>();
    List<Job> inActiveJob = new ArrayList<>();
    List<Job> activeJob = new ArrayList<>();

    public AdminMenu(LoginService loginService, Scanner scanner, List<Account> inactiveAccount, List<Account> activeAccount, List<Job> inActiveJob, List<Job> activeJob) {
        this.loginService = loginService;
        this.scanner = scanner;
        this.inactiveAccount = inactiveAccount;
        this.activeAccount = activeAccount;
        this.inActiveJob = inActiveJob;
        this.activeJob = activeJob;
    }

    void mainMenu () {
        System.out.println("\t1. Duyet tai khoan ");
        System.out.println("\t2. Duyet cong viec ");
        System.out.println("\t3. Xoa cong viec ");
        System.out.println("\t4. Xoa tai khoan");
    }

    void approveAccount () {
        new AdminService(loginService, scanner, inactiveAccount, activeAccount, inActiveJob, activeJob).approveAccount();
    }

    void approveJob () {
            new AdminService(loginService, scanner, inactiveAccount, activeAccount, inActiveJob, activeJob).approveJob();
    }

    void removeJob () {
            new AdminService(loginService, scanner, inactiveAccount, activeAccount, inActiveJob, activeJob).removeJob();
    }

    void removeAccount () {
            new AdminService(loginService, scanner, inactiveAccount, activeAccount, inActiveJob, activeJob).removeAccount();
    }

}
