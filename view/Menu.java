package view;

import service.JobService;
import service.LoginService;
import service.RegisterService;

import java.util.Scanner;

public class Menu {
    RegisterService registerService = new RegisterService();
    JobService jobService = new JobService();
    LoginService loginService = new LoginService();

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
                case 2:
                    System.out.println("Ban la nguoi giao viec hay nguoi tim viec?\n 1. Nguoi giao viec\n 2. Nguoi tim viec\n 3. Admin");
                    System.out.printf("Nhap lua chon cua ban: ");
                    int choice2 = Integer.parseInt(scanner.nextLine());
                    switch (choice2) {
                        case 1:
                            registerService.createPosterAccount(scanner);
                            break;
                        case 2:
                            registerService.createFinderAccount(scanner);
                            break;
                        case 3:
                            registerService.createAdminAccount(scanner);
                            break;
                    }
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }
    }
}
