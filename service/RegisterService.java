package service;

import database.AccountData;
import entities.Account;

import java.util.Scanner;

public class RegisterService {
    public Account createAccount(Scanner scanner) {
        System.out.print("Username: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nhap ho va ten: ");
        String name = scanner.nextLine();
        System.out.print("Nhap ngay thang nam sinh: ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        return new Account(userName, password, Account.Role.GUEST, name, dateOfBirth, phoneNumber, email);
    }

    //    adminService
    public void createAdminAccount(Scanner scanner) {
        Account account = createAccount(scanner);
        account.setRole(Account.Role.ADMIN);
        AccountData.addAccount(account);
        System.out.println("Tai khoan cua ban dang duoc doi ngu admin duyet, ket qua se duoc bao ve email");
    }

    //    finderService
    public void createFinderAccount(Scanner scanner) {
        Account account = createAccount(scanner);
        account.setRole(Account.Role.FINDER);
        AccountData.addAccount(account);
        System.out.println("Tai khoan cua ban dang duoc doi ngu admin duyet, ket qua se duoc bao ve email");
    }

    //    posterService
    public void createPosterAccount(Scanner scanner) {
        Account account = createAccount(scanner);
        account.setRole(Account.Role.POSTER);
        AccountData.addAccount(account);
        System.out.println("Tai khoan cua ban dang duoc doi ngu admin duyet, ket qua se duoc bao ve email");
    }
}
