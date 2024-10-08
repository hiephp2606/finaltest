package service;

import database.AccountData;
import entities.Account;

import java.util.Scanner;

public class LoginService {
    public Account who;

    public void accountLogin(Scanner scanner) {
        while (true) {
            System.out.print("Nhap username: ");
            String username = scanner.nextLine();
            System.out.print("Nhap password: ");
            String password = scanner.nextLine();

            who = AccountData.matchUsername(username);

            if (who == null) {
                System.out.println("Tai khoan nay khong ton tai!");
            } else if (!AccountData.matchPassword(who, password)) {
                System.out.println("Sai mat khau!");
            } else {
                System.out.println("Dang nhap thanh cong!");
            }
        }
    }
}
