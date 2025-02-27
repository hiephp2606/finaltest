package utils;

import java.util.List;
import java.util.Scanner;

public class InputUtils {
    public static String loopInputString(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validateString(value)) {
                return value;
            }
        } while (true);
    }

    public static Integer loopInputInteger(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validateInteger(value)) {
                return Integer.parseInt(value);
            }
        } while (true);
    }

    public static String loopInputDate(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validateDate(value)) {
                return value;
            }
        } while (true);
    }

    public static String loopInputEmail(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validateEmail(value)) {
                return value;
            }
        } while (true);
    }

    public static String loopInputPassword(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validatePassword(value)) {
                return value;
            }
        } while (true);
    }

    public static int loopInputPhoneNumber(String message, Scanner scanner) {
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validatePhoneNumber(value)) {
                return Integer.parseInt(value);
            }
        } while (true);
    }

    public static int loopInputChoice(List<String> message, Scanner scanner, int begin, int end) {
        int choice;
        do {
            for (String m : message) {
                System.out.println(m);
            }
            choice = loopInputInteger("Nhap lua chon cua ban: ", scanner);
        } while (choice < begin || choice > end);
        return choice;
    }

    public static String loopInputPath(String message, Scanner scanner) {
        String pathString;
        do {
            System.out.print(message);
            pathString = scanner.nextLine();
            if (ValidationUtils.validatePath(pathString)) {
                return pathString;
            } else {
                System.out.println("File khong ton tai!");
            }
        } while (true);
    }
}
