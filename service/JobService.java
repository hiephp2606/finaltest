package service;

import database.JobData;
import entities.Account;
import entities.Job;

import java.util.Scanner;

public class JobService {

    public Job createJob(Account account, Scanner scanner) {
        System.out.print("Nhap thong tin co ban cua cong viec cua ban: ");
        String jobTitle = scanner.nextLine();
        System.out.print("Mo ta cong viec: ");
        String jobDescribe = scanner.nextLine();
        System.out.print("Nhap so luong nhan vien ban muon tuyen: ");
        int employeeNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhap luong cua cong viec theo thang: ");
        int salary = Integer.parseInt(scanner.nextLine());
        Job.WorkPlaceType workPlaceType = typeChoice(scanner);
        Job.TimeType timeType = timeChoice(scanner);
        Job job = new Job(account.getId(), workPlaceType, timeType, jobTitle, jobDescribe, employeeNumber, salary, Job.Status.AVAILABLE);
        JobData.saveJob(job);
        System.out.println("Tao cong viec thanh cong!");
        return job;
    }

//    typeService
    public Job.WorkPlaceType typeChoice(Scanner scanner) {
        System.out.print("Chon noi lam viec: \n  1. Lam o nha\n  2. Lam ben ngoai");
        int choiceWP = Integer.parseInt(scanner.nextLine());
        switch (choiceWP) {
            case 1:
                return Job.WorkPlaceType.WFH;
            case 2:
                return Job.WorkPlaceType.OS;
            default:
                System.out.println("Cap nhat sau!");
                return Job.WorkPlaceType.NULL;
        }
    }

    public Job.TimeType timeChoice(Scanner scanner) {
        System.out.print("Chon tinh chat thoi gian cong viec: \n  1. full time\n  2. part time");
        int choiceWT = Integer.parseInt(scanner.nextLine());
        switch (choiceWT) {
            case 1:
                return Job.TimeType.FT;
            case 2:
                return Job.TimeType.PT;
            default:
                System.out.println("Cap nhat sau!");
                return Job.TimeType.NULL;
        }
    }
}
