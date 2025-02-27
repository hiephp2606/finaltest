package view;

import database.JobData;
import entities.Account;
import service.common.LoginService;
import service.common.RegisterService;
import service.byrole.FinderService;
import service.byrole.GuruService;
import service.byrole.PosterService;
import utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    LoginService loginService = new LoginService();
    RegisterService registerService = new RegisterService();
    FinderMenu finderMenu = new FinderMenu(loginService, JobData.getJobList(), scanner);
    PosterMenu posterMenu = new PosterMenu(loginService, scanner);
    AdminMenu adminMenu = new AdminMenu(loginService, scanner);
    GuruMenu guruMenu = new GuruMenu(loginService, scanner);

    public void beginMenuSelect (Scanner scanner) {
        boolean loop = true;
        while (loop == true) {
            int choice1 = InputUtils.loopInputChoice(List.of("===========TechMaster Work===========","\t1. Dang nhap\n\t2. Dang ky"), scanner, 1, 2 );
            switch (choice1) {
                case 1:
                    try {
                        loginService.accountLogin(scanner);
                        if (loginService.who.getAccountStatus().equals(Account.AccountStatus.ACTIVE)) {
                            if (loginService.who.getRole().equals(Account.Role.FINDER)) {
                                finderMenuService();
                            }

                            else if (loginService.who.getRole().equals(Account.Role.POSTER)) {
                                posterMenuService();
                            }

                            else if (loginService.who.getRole().equals(Account.Role.ADMIN)) {
                                adminMenuService();
                            }

                            else if (loginService.who.getRole().equals(Account.Role.GURU)) {
                                guruMenuService();
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Tai khoan nay khong ton tai hoac da bi tu choi duyet!");
                        break;
                    }

                    break;
                case 2:
                    int choice2 = InputUtils.loopInputChoice(
                            List.of(
                                    "\t1. Nguoi tim viec",
                                    "\t2. Nguoi giao viec",
                                    "\t3. Admin",
                                    "\t4. Thoat"
                            ),
                            scanner,
                            1,
                            4);
                    switch (choice2) {
                        case 1:
                            registerService.createFinderAccount(scanner);
                            break;
                        case 2:
                            registerService.createPosterAccount(scanner);
                            break;
                        case 3:
                            registerService.createAdminAccount(scanner);
                            break;
                        case 4:

                            break;
                    }
                    break;
            }
        }
    }


    public void finderMenuService () {
        boolean loop = true;
        do {
            finderMenu.mainMenu();
            System.out.print("Nhap lua chon cua ban: ");
            int Choice = InputUtils.loopInputInteger("", scanner);
            switch (Choice) {
                case 1:
                    finderMenu.listJob();
                    break;

                case 2:
                    finderMenu.findJob();
                    break;

                case 3:
                    finderMenu.listAppliedJob();
                    break;

                case 4:
                    finderMenu.listAcceptedJob();
                    break;

                case 5:
                    finderMenu.listRejectedJob();
                    break;

                case 6:
                    finderMenu.removeRequestJob();
                    break;
                case 7:
                    finderMenu.updatePassword();
                    System.out.println("Cap nhat mat khau thanh cong!");
                    break;
                case 8:
                    finderMenu.updateEmail();
                    System.out.println("Cap nhat email thanh cong!");
                    break;
                case 9:
                    finderMenu.updatePhonenumber();
                    System.out.println("Cap nhat so dien thoai thanh cong!");
                    break;
                case 10:
                    loop = false;
                    break;
            }
        } while (loop == true);
    }
    public void posterMenuService () {
        boolean loop = true;
        do {
            posterMenu.mainMenu();
            System.out.print("Nhap lua chon cua ban: ");
            int Choice = InputUtils.loopInputInteger("", scanner);
            switch (Choice) {
                case 1:
                    posterMenu.viewJob();
                    break;

                case 2:
                    posterMenu.viewApplicant();
                    break;

                case 3:
                    posterMenu.createJob();
                    break;

                case 4:
                    posterMenu.deleteJob();
                    break;

                case 5:
                    posterMenu.viewApproveJobRequest();
                    break;
                case 6:
                    posterMenu.updatePassword();
                    System.out.println("Cap nhat mat khau thanh cong!");
                    break;
                case 7:
                    posterMenu.updateEmail();
                    System.out.println("Cap nhat email thanh cong!");
                    break;
                case 8:
                    posterMenu.updatePhonenumber();
                    System.out.println("Cap nhat so dien thoai thanh cong!");
                    break;
                case 9:
                    loop = false;
                    break;

            }
        } while (loop == true);
    }
    public void adminMenuService () {
        boolean loop = true;
        do {
            adminMenu.mainMenu();
            System.out.print("Nhap lua chon cua ban: ");
            int Choice = InputUtils.loopInputInteger("", scanner);
            switch (Choice) {
                case 1:
                    adminMenu.approveAccount();
                    break;

                case 2:
                    adminMenu.approveJob();
                    break;

                case 3:
                    adminMenu.removeJob();
                    break;

                case 4:
                    adminMenu.removeAccount();
                    break;
                case 5:
                    adminMenu.updatePasword();
                    System.out.println("Cap nhat mat khau thanh cong!");
                    break;
                case 6:
                    adminMenu.updateEmail();
                    System.out.println("Cap nhat email thanh cong!");
                    break;
                case 7:
                    adminMenu.updatePhonenumber();
                    System.out.println("Cap nhat so dien thoai thanh cong!");
                    break;
                case 8:
                    loop = false;
                    break;
            }
        } while (loop == true);
    }
    public void guruMenuService () {
        boolean loop = true;
        do {
            guruMenu.mainMenu();
            System.out.print("Nhap lua chon cua ban: ");
            int Choice = InputUtils.loopInputInteger("", scanner);
            switch (Choice) {
                case 1:
                    guruMenu.approveAccount();
                    break;
                case 2:
                    guruMenu.approveJob();
                    break;

                case 3:
                    guruMenu.removeJob();
                    break;

                case 4:
                    guruMenu.removeAccount();
                    break;
                case 5:
                    guruMenu.updatePassword();
                    System.out.println("Cap nhat mat khau thanh cong!");
                    break;
                case 6:
                    guruMenu.updateEmail();
                    System.out.println("Cap nhat email thanh cong!");
                    break;
                case 7:
                    guruMenu.updatePhonenumber();
                    System.out.println("Cap nhat so dien thoai thanh cong");
                    break;
                case 8:
                    loop = false;
                    break;
            }
        } while (loop == true);
    }

}
