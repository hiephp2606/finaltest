package utils;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean validateString(String string) {
        return !string.isEmpty();
    }

    public static boolean validateInteger(String intString) {
        try {
            Integer.parseInt(intString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateDate(String dateString) {
        try {
            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validatePassword(String passwordString) {
        try {
            Pattern emailRegex = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{7,15}$");
            Matcher p = emailRegex.matcher(passwordString);
            return p.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateEmail(String emailString) {
        try {
            Pattern emailRegex = Pattern.compile("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$");
            Matcher m = emailRegex.matcher(emailString);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validatePhoneNumber(String phoneString) {
        try {
            Pattern emailRegex = Pattern.compile("^[0-9]{6,12}$");
            Matcher m = emailRegex.matcher(phoneString);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validatePath(String pathString) {
        try {
            File f = Path.of(pathString).toFile();
            return f.exists();
        } catch (Exception e) {
            return false;
        }
    }
}
