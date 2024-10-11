package utils;

import database.AccountData;
import entities.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {

    public static int inputInteger(Scanner scanner){
        do {
            try {
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập vào số nguyên");
                System.out.print("Nhap lua chon cua ban: ");
            }
        } while (true);
    }

    public static String inputString(Scanner scanner){
        do {
            String string = scanner.nextLine();
            if (string.isEmpty()) {
                System.out.println("Bạn vui lòng nhập day du");
            } else {
                return string;
            }
        } while (true);
    }

    public static int inputPhoneNumber(Scanner scanner){
        do {
            int phoneNumber;
            try {
                phoneNumber = Integer.parseInt(scanner.nextLine());
                if (checkPhoneNumber(phoneNumber) == null) {
                    return phoneNumber;
                }
                else {
                    System.out.println("So dien thoai nay da duoc su dung hoac dang duoc dang ky, vui long chon so dien thoai khac!");
                }
            } catch (Exception e){
                System.out.println("So dien thoai trong hoac khog dung dinh dang!");
            }
            System.out.println("Vui long nhap lai so dien thoai: ");
        } while (true);
    }

    public static Account checkPhoneNumber(Integer phoneNumber) {
        for (Account account : AccountData.getList()) {
            if (phoneNumber.equals(account.getPhoneNumber())) {
                return  account;
            }
        }
        return null;
    }

    public static int inputId(Scanner scanner){
        do {
            try {
                System.out.print("Nhap id: ");
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch (Exception e){
                System.out.println("Chi duoc nhap so dien thoai");
            }
        } while (true);
    }


    public static String inputDate(Scanner scanner) {
        do {
            try {
                String dateOfBirth = scanner.nextLine();
                LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return dateOfBirth;
            } catch (Exception e) {
                System.out.println("Ngay sinh khong hop le, vui long nhap lai");
            }
        } while (true);
    }

    public static String inputEmail(Scanner scanner) {
        do {
            String email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("email khong the de trong");
            } else if (checkEmail(email) != null) {
                System.out.println("email nay da duoc su dung hoac dang duoc dang ky, vui long chon email khac!");
            }
            else {
                return email;
            }
        } while (true);
    }

    public static Account checkEmail (String email) {
        for (Account account : AccountData.getList()) {
            if (email.equals(account.getEmail())) {
                return account;
            }
        }
        return null;
    }
}
