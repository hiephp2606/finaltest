package view;

import entities.Job;
import service.byrole.FinderService;
import service.byrole.PosterService;
import service.common.LoginService;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinderMenu {
    LoginService loginService;
    List<Job> displayedList = new ArrayList<>();
    Scanner scanner;

    public FinderMenu(LoginService loginService, List<Job> displayedList, Scanner scanner) {
        this.loginService = loginService;
        this.displayedList = displayedList;
        this.scanner = scanner;
    }

    void mainMenu() {
        System.out.println("===========Chao mung " + loginService.who.getUsername() + " ===========");
        System.out.println("\t1. Xem danh sach cong viec");
        System.out.println("\t2. Tim cong viec");
        System.out.println("\t3. Xem cong viec da dang ky");
        System.out.println("\t4. Xem cong viec da duoc nhan");
        System.out.println("\t5. Xem cong viec bi tu choi");
        System.out.println("\t6. Huy dang ky cong viec");
        System.out.println("\t7. Cap nhat mat khau");
        System.out.println("\t8. Cap nhat email");
        System.out.println("\t9. Cap nhat so dien thoai");
        System.out.println("\t10. Dang xuat");
    }

    void listJob() {
        new FinderService(loginService, scanner).getPost();
    }

    void findJob() {
        FinderService finderService = new FinderService(loginService, scanner);
        while (true) {
            int choice = InputUtils.loopInputChoice(
                    List.of(
                            "Chon bo loc:",
                            "\t1. Loc theo tieu de",
                            "\t2. Loc theo muc luong",
                            "\t3. Loc theo noi lam viec",
                            "\t4. Loc theo thoi gian lam viec",
                            "\t5. Xin viec",
                            "\t6. Thoat"
                    ), scanner,
                    1,
                    6
            );
            switch (choice) {
                case 1:
                    finderService.filterByTitle();
                    break;
                case 2:
                    finderService.filterSalary();
                    break;
                case 3:
                    finderService.filterJobByWorkplace();
                    break;
                case 4:
                    finderService.filterByWorkTime();
                    break;
                case 5:
                    finderService.applyJob();
                    return;
                default:
                    return;
            }
        }
    }

    void listAppliedJob() {
        new FinderService(loginService, scanner).getJobRequest();
    }

    void listAcceptedJob() {
        new FinderService(loginService, scanner).acceptedJob();
    }

    void listRejectedJob() {
        new FinderService(loginService, scanner).rejectedJob();
    }

    void removeRequestJob() {
        new FinderService(loginService, scanner).removeJobRequest();
    }

    void updateEmail() {new FinderService(loginService, scanner).updateEmail(); }

    void updatePassword() {new FinderService(loginService, scanner).updatePassword(); }

    void updatePhonenumber() {new PosterService(loginService, scanner).updatePhonenumber(); }
}
