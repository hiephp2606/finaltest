package view;

import entities.Account;
import service.JobService;
import service.LoginService;
import service.RegisterService;
import service.byrole.AdminService;
import service.byrole.FinderService;
import service.byrole.GuruService;
import service.byrole.PosterService;

import java.util.Scanner;

public class Menu {
    LoginService loginService = new LoginService();
    RegisterService registerService = new RegisterService();
    FinderService finderService;
    PosterService posterService;
    AdminService adminService;
    GuruService guruService;
    FinderMenu finderMenu;
    PosterMenu posterMenu;
    AdminMenu adminMenu;
    GuruMenu guruMenu;
    Scanner scanner = new Scanner(System.in);

    public void beginMenuDisplay () {
        System.out.println("===========TechMaster Work===========");
        System.out.println(" 1. Dang nhap\n 2. Dang ky");
    }

    public void beginMenuSelect (Scanner scanner) {
        boolean loop = true;
        while (loop == true) {
            beginMenuDisplay();
            System.out.printf("Nhap lua chon cua ban: ");
            int choice1 = Integer.parseInt(scanner.nextLine());
            switch (choice1) {
                case 1:
                        loginService.accountLogin(scanner);
                        if (loginService.who.getRole().equals(Account.Role.FINDER)) {
                            finderMenuService();
                        }

                        if (loginService.who.getRole().equals(Account.Role.POSTER)) {
                            posterMenuService();
                        }

                        if (loginService.who.getRole().equals(Account.Role.ADMIN)) {
                            adminMenuService();
                        }

                        if (loginService.who.getRole().equals(Account.Role.GURU)) {
                            guruMenuService();
                        }

                        if (loginService.who == null) {
                            break;
                        }
                    break;
                case 2:
                    System.out.println("Chon quyen dang nhap");
                    System.out.println("\t1. Nguoi tim viec");
                    System.out.println("\t2. Nguoi giao viec");
                    System.out.println("\t3. Admin");
                    System.out.print("Nhap lua chon cua ban: ");
                    int choice2 = Integer.parseInt(scanner.nextLine());
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
                    }
                    break;
            }
        }
    }


    public void finderMenuService () {
        finderMenu.mainMenu();
        System.out.print("Nhap lua chon cua ban: ");
        int Choice = Integer.parseInt(scanner.nextLine());
        switch (Choice) {
            case 1:
                finderMenu.listJob();
                break;

            case 2:
                finderMenu.findJob();
                break;

            case 3:
                finderMenu.listAplliedJob();
                break;

            case 4:
                finderMenu.listAccpetedJob();
                break;

            case 5:
                finderMenu.listRejectedJob();
                break;

            case 6:
                finderMenu.removeRequestJob();
                break;
        }

    }
    public void posterMenuService () {
        posterMenu.mainMenu();
        System.out.print("Nhap lua chon cua ban: ");
        int Choice = Integer.parseInt(scanner.nextLine());
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
        }

    }
    public void adminMenuService () {
        adminMenu.mainMenu();
        System.out.print("Nhap lua chon cua ban: ");
        int Choice = Integer.parseInt(scanner.nextLine());
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
        }

    }
    public void guruMenuService () {
        guruMenu.mainMenu();
        System.out.print("Nhap lua chon cua ban: ");
        int Choice = Integer.parseInt(scanner.nextLine());
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
        }

    }

}
