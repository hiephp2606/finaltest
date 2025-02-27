package service.common;

import database.AccountData;
import entities.Account;
import utils.InputUtils;

import java.util.Scanner;

public class RegisterService {
    public Account createAccount(Scanner scanner) {
        String userName;
        do {
            userName = InputUtils.loopInputString("Username: ", scanner);
        } while (checkUsername(userName) != null);

        String password = InputUtils.loopInputPassword("Nhap password (7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt)", scanner);
        String name = InputUtils.loopInputString("Nhap ho va ten: ", scanner);
        String dateOfBirth = InputUtils.loopInputDate("Nhap ngay/thang/nam sinh: ", scanner);

        int phoneNumber;
        do {
            phoneNumber = InputUtils.loopInputPhoneNumber("Nhap so dien thoai: ", scanner);
        } while (checkPhoneNumber(phoneNumber) != null);

        String email;
        do {
            email = InputUtils.loopInputEmail("Nhap email: ", scanner);
        } while (checkEmail(email) != null);
        return new Account(userName, password, Account.Role.GUEST, name, dateOfBirth, phoneNumber, email, Account.AccountStatus.INACTIVE);
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

//    checkService
    public Account checkUsername (String userName) {
        for (Account account : AccountData.getList()) {
            if (userName.equals(account.getUsername())) {
                System.out.println("Username nay da duoc su dung, vui long chon username khac!");
                return account;
            }
        }

        return null;
    }

    public Account checkEmail (String email) {
        for (Account account : AccountData.getList()) {
            if (email.equals(account.getEmail())) {
                System.out.println("Email nay da duoc su dung, vui long chon email khac!");
                return account;
            }
        }

        return null;
    }

    public Account checkPhoneNumber (Integer phoneNumber) {
        for (Account account : AccountData.getList()) {
            if (phoneNumber.equals(account.getPhoneNumber())) {
                System.out.println("So dien thoai nay da duoc su dung, vui long chon so khac!");
                return account;
            }
        }

        return null;
    }
}
