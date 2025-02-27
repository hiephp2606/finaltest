package service.common;

import database.AccountData;
import entities.Account;
import utils.InputUtils;

import java.util.Scanner;

public class LoginService {
    public Account who;

    public void accountLogin(Scanner scanner) {
        boolean loop = true;
        do {
            System.out.print("Nhap username: ");
            String username = scanner.nextLine();
            System.out.print("Nhap password: ");
            String password = scanner.nextLine();

            who = AccountData.matchUsername(username);

            if (!AccountData.matchPassword(who, password)) {
                System.out.println("Sai mat khau!");
                System.out.print("Quen mat khau? [Y/N]: ");
                do {
                    String answer = InputUtils.loopInputString("", scanner);
                    if (answer.equalsIgnoreCase("y")) {
                        String email = InputUtils.loopInputEmail("Hay nhap email de xac thuc va cap nhat mat khau: ", scanner);
                        if (AccountData.getAccountById(who.getId()).getEmail().equals(email) ) {
                            String passwordUpdate = InputUtils.loopInputPassword("Nhap mat khau moi: ", scanner);
                            AccountData.getAccountById(who.getId()).setPassword(passwordUpdate);
                            System.out.println("Cap nhat mat khau thanh cong!");
                            loop = false;
                            break;
                        }
                        else {
                            System.out.println("Email khong chinh xac");
                        }
                        break;
                    } else if (answer.equalsIgnoreCase("n")) {
                        break;
                    } else {
                        System.out.println("Khong co lua chon nay, xin vui long nhap lai");
                    }
                } while (true);

            } else if (who.getAccountStatus().equals(Account.AccountStatus.INACTIVE)) {
                System.out.println("Tai khoan cua ban chua duoc duyet!");
            } else {
                System.out.println("Dang nhap thanh cong!");
                break;
            }
        } while (loop == true);
    }
}
