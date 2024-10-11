package view;

import entities.Account;
import entities.Job;
import service.LoginService;
import service.byrole.GuruService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuruMenu {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);

    public GuruMenu(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    void mainMenu () {
        System.out.println("===========Chao mung " + loginService.who.getUsername() + " ===========");
        System.out.println("\t1. Duyet tai khoan ");
        System.out.println("\t2. Duyet cong viec ");
        System.out.println("\t3. Xoa cong viec ");
        System.out.println("\t4. Xoa tai khoan ");
        System.out.println("\t5. Dang xuat");

    }

    void approveAccount () {
        new GuruService(loginService, scanner).approveAccount();
    }

    void approveJob () {
            new GuruService(loginService, scanner).approveJob();
    }

    void removeJob () {
            new GuruService(loginService, scanner).deletePost();
    }

    void removeAccount () {
            new GuruService(loginService, scanner).deleteAccount();
    }

}
