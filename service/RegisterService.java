package service;

import database.AccountData;
import entities.Account;
import ultis.Ultis;

import java.util.Scanner;

public class RegisterService {
    public Account createAccount(Scanner scanner) {
        String userName = "1";
        do {
            System.out.print("Username: ");
            String userNameCheckType = scanner.nextLine();
            if (userNameCheckType == null) {
                System.out.println("Username nay da duoc su dung hoac dang duoc dang ky, vui long chon username khac!");
            }
            else {
                userName = userNameCheckType;
                break;
            }
        } while (true);

        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nhap ho va ten: ");
        String name = scanner.nextLine();
        System.out.print("Nhap ngay thang nam sinh: ");
        String dateOfBirth = scanner.nextLine();
        int phoneNumber = 0;
        do {
            Integer phoneNumberCheckType = Ultis.inputPhoneNumber(scanner);
            if (phoneNumberCheckType == null) {
                System.out.println("So dien thoai nay da duoc su dung hoac dang duoc dang ky, vui long chon username khac!");
            }
            else {
                phoneNumber = phoneNumberCheckType;
                break;
            }
        } while (true);

        String email = "1";
        do {
            System.out.print("Email: ");
            String emailCheckType = scanner.nextLine();
            if (emailCheckType == null) {
                System.out.println("email nay da duoc su dung hoac dang duoc dang ky, vui long chon username khac!");
            }
            else {
                email = emailCheckType;
                break;
            }
        } while (true);

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
    public String checkUsername (String userName) {
        for (Account account : AccountData.getList()) {
            if (userName.equals(account.getUsername())) {
                return null;
            }
        }

        return userName;
    }

    public String checkEmail (String email) {
        for (Account account : AccountData.getList()) {
            if (email.equals(account.getEmail())) {
                return null;
            }
        }

        return email;
    }

    public Integer checkPhoneNumber (Integer phoneNumber) {
        for (Account account : AccountData.getList()) {
            if (phoneNumber.equals(account.getPhoneNumber())) {
                return null;
            }
        }

        return phoneNumber;
    }


}
