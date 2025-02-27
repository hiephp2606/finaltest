package view;

import service.common.LoginService;
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
        System.out.println("===========Chao mung " + loginService.who.getUsername() + "===========");
        System.out.println("1. Xem cong viec da tao");
        System.out.println("2. Xem don ung tuyen");
        System.out.println("3. Them cong viec");
        System.out.println("4. Xoa cong viec");
        System.out.println("5. Phe duyet don");
        System.out.println("6. Thay doi mat khau");
        System.out.println("7. Thay doi email");
        System.out.println("8. Thay doi so dien thoai");
        System.out.println("9. Dang xuat");
    }

    void viewJob() {
        new PosterService(loginService, scanner).getPost();
    }

    void viewApplicant() {new PosterService(loginService, scanner).getJobRequest(); }

    void createJob() {
        new PosterService(loginService, scanner).createPost();
    }

    void deleteJob() {
        new PosterService(loginService, scanner).deletePost();
    }

    void viewApproveJobRequest() {
        new PosterService(loginService, scanner).approveJobRequest();
    }

    void updateEmail() {new PosterService(loginService, scanner).updateEmail(); }

    void updatePassword() {new PosterService(loginService, scanner).updatePassword(); }

    void updatePhonenumber() {new PosterService(loginService, scanner).updatePhonenumber(); }
}
