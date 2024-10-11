package utils;

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

    public static int loopInputPhoneNumber(String message, Scanner scanner){
        do {
            System.out.print(message);
            String value = scanner.nextLine();
            if (ValidationUtils.validatePhoneNumber(value)) {
                return Integer.parseInt(value);
            }
        } while (true);
    }
}
