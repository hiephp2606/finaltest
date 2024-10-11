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

    public AdminMenu(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    void mainMenu () {
        System.out.println("\t1. Duyet tai khoan ");
        System.out.println("\t2. Duyet cong viec ");
        System.out.println("\t3. Xoa cong viec ");
        System.out.println("\t4. Xoa tai khoan");
    }

    void approveAccount () {
        new AdminService(loginService, scanner).approveAccount();
    }

    void approveJob () {
            new AdminService(loginService, scanner).approveJob();
    }

    void removeJob () {
            new AdminService(loginService, scanner).removeJob();
    }

    void removeAccount () {
            new AdminService(loginService, scanner).removeAccount();
    }

}
