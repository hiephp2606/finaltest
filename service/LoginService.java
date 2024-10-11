package service;

import database.AccountData;
import entities.Account;

import java.util.Scanner;

public class LoginService {
    public Account who;

    public void accountLogin(Scanner scanner) {
        System.out.print("Nhap username: ");
        String username = scanner.nextLine();
        System.out.print("Nhap password: ");
        String password = scanner.nextLine();

        who = AccountData.matchUsername(username);

        if (!AccountData.matchPassword(who, password)) {
            System.out.println("Sai mat khau!");
        } else if (who.getAccountStatus().equals(Account.AccountStatus.INACTIVE)) {
            System.out.println("Tai khoan cua ban chua duoc duyet!");
        } else {
            System.out.println("Dang nhap thanh cong!");
        }

    }
}
