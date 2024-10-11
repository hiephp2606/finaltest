package view;

import database.JobData;
import entities.Account;
import service.JobService;
import service.LoginService;
import service.RegisterService;
import service.byrole.AdminService;
import service.byrole.FinderService;
import service.byrole.GuruService;
import service.byrole.PosterService;
import ultis.Ultis;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    LoginService loginService = new LoginService();
    RegisterService registerService = new RegisterService();
    JobService jobService = new JobService();
    FinderService finderService;
    PosterService posterService;
    AdminService adminService ;
    GuruService guruService;
    FinderMenu finderMenu = new FinderMenu(loginService, JobData.getJobList(), jobService, scanner);
    PosterMenu posterMenu = new PosterMenu(loginService, scanner);
    AdminMenu adminMenu = new AdminMenu(loginService, scanner);
    GuruMenu guruMenu = new GuruMenu(loginService, scanner);

    public void beginMenuDisplay () {
        System.out.println("===========TechMaster Work===========");
        System.out.println("\t1. Dang nhap\n\t2. Dang ky");
    }

    public void beginMenuSelect (Scanner scanner) {
        boolean loop = true;
        while (loop == true) {
            beginMenuDisplay();
            int choice1 = Ultis.inputInteger(scanner);
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
                    System.out.println("Chon quyen dang nhap");
                    System.out.println("\t1. Nguoi tim viec");
                    System.out.println("\t2. Nguoi giao viec");
                    System.out.println("\t3. Admin");
                    System.out.println("\t4. Thoat");
                    int choice2 = Ultis.inputInteger(scanner);
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
            int Choice = Ultis.inputInteger(scanner);
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
                case 7:
                    loop = false;
                    break;
            }
        } while (loop == true);
    }
    public void posterMenuService () {
        boolean loop = true;
        do {
            posterMenu.mainMenu();
            int Choice = Ultis.inputInteger(scanner);
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
                    loop = false;
                    break;
            }
        } while (loop == true);
    }
    public void adminMenuService () {
        boolean loop = true;
        do {
            adminMenu.mainMenu();
            int Choice = Ultis.inputInteger(scanner);
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
                    loop = false;
                    break;
            }
        } while (loop == true);
    }
    public void guruMenuService () {
        boolean loop = true;
        do {
            guruMenu.mainMenu();
            int Choice = Ultis.inputInteger(scanner);
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
                    loop = false;
                    break;
            }
        } while (loop == true);
    }

}
