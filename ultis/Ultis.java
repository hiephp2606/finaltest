package ultis;

import java.util.Scanner;

public class Ultis {
    public static int inputInteger(Scanner scanner){
        do {
            try {
                System.out.printf("Nhap lua chon cua ban: ");
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch (Exception e){
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập vào số nguyên");
            }
        } while (true);
    }

    public static String inputString(Scanner scanner){
        do {
            try {
                String string = scanner.nextLine();
                return string;
            } catch (Exception e){
                System.out.println("Bạn vui lòng nhập day du");
            }
        } while (true);
    }

    public static int inputPhoneNumber(Scanner scanner){
        do {
            try {
                System.out.print("Nhap so dien thoai: ");
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch (Exception e){
                System.out.println("Chi duoc nhap so dien thoai");
            }
        } while (true);
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

}
