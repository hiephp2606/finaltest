package service;

import database.AccountData;
import entities.Account;
import ultis.Ultis;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegisterService {
    public Account createAccount(Scanner scanner) {
        String userName = "1";
        do {
            System.out.print("Username: ");
            String userNameCheckType = scanner.nextLine();
            if (checkUsername(userNameCheckType) == null) {
                userName = userNameCheckType;
                break;
            }
            else {
                System.out.println("Username nay da duoc su dung hoac dang duoc dang ky, vui long chon username khac!");
            }
        } while (true);

        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nhap ho va ten: ");
        String name = scanner.nextLine();

        String dateOfBirth;
        do {
            try {
                System.out.print("Nhap ngay thang nam sinh: ");
                dateOfBirth = scanner.nextLine();
                LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (Exception e) {
                System.out.println("Ngay sinh khong hop le, vui long nhap lai");
            }
        } while (true);

        int phoneNumber = 0;
        do {
            Integer phoneNumberCheckType = Ultis.inputPhoneNumber(scanner);
            if (checkPhoneNumber(phoneNumberCheckType) == null) {
                phoneNumber = phoneNumberCheckType;
                break;
            }
            else {
                System.out.println("So dien thoai nay da duoc su dung hoac dang duoc dang ky, vui long chon so dien thoai khac!");
            }
        } while (true);

        String email = "1";
        do {
            System.out.print("Email: ");
            String emailCheckType = scanner.nextLine();
            if (checkEmail(emailCheckType) == null) {
                email = emailCheckType;
                break;
            }
            else {
                System.out.println("email nay da duoc su dung hoac dang duoc dang ky, vui long chon email khac!");
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
    public Account checkUsername (String userName) {
        for (Account account : AccountData.getList()) {
            if (userName.equals(account.getUsername())) {
                return account;
            }
        }

        return null;
    }

    public Account checkEmail (String email) {
        for (Account account : AccountData.getList()) {
            if (email.equals(account.getEmail())) {
                return account;
            }
        }

        return null;
    }

    public Account checkPhoneNumber (Integer phoneNumber) {
        for (Account account : AccountData.getList()) {
            if (phoneNumber.equals(account.getPhoneNumber())) {
                return  account;
            }
        }

        return null;
    }


}
