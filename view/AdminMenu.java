package view;

import service.byrole.GuruService;
import service.byrole.PosterService;
import service.common.LoginService;

import java.util.Scanner;

public class AdminMenu {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);

    public AdminMenu(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    void mainMenu () {
        System.out.println("===========Chao mung " + loginService.who.getUsername() + " ===========");
        System.out.println("\t1. Duyet tai khoan ");
        System.out.println("\t2. Duyet cong viec ");
        System.out.println("\t3. Xoa cong viec ");
        System.out.println("\t4. Xoa tai khoan ");
        System.out.println("\t5. Cap nhat mat khau ");
        System.out.println("\t6. Cap nhat email ");
        System.out.println("\t7. Cap nhat so dien thoai ");
        System.out.println("\t8. Dang xuat");

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

    void updatePasword () {new GuruService(loginService, scanner).updatePassword(); }

    void updateEmail () {new GuruService(loginService, scanner).updateEmail(); }

    void updatePhonenumber() {new PosterService(loginService, scanner).updatePhonenumber(); }



}
