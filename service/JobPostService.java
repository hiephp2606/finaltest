package service;

import database.JobData;
import entities.Account;
import entities.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobPostService {
    List<Job> displayedList = new ArrayList<>();
    JobService jobService = new JobService();

//    Tao post
    public void createPost (Account account, Scanner scanner) {
        jobService.createJob(account, scanner);
    }

//    listJob
    public void listJob () {
        for (Job job : JobData.getJobList()) {
            System.out.print(job.printBrief());
        }
    }

//    findFilter
    public void findJob (Scanner scanner) {
        System.out.print("Nhap cong viec ban muon tim: ");
        String jobNeedle = scanner.nextLine();
        for (Job job : JobData.getJobList()) {
            if (job.getJobTitle().contains(jobNeedle)) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterJobByWorkplace (Scanner scanner) {
        System.out.println("Loc theo noi lam viec:");
        System.out.println("\t1. Lam o nha");
        System.out.println("\t2. Lam tai tru so");
        int WPchoice = Integer.parseInt(scanner.nextLine());

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getPlaceType().ordinal() == WPchoice) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterJobByWorkTime (Scanner scanner) {
        System.out.println("Loc theo thoi gian lam viec:");
        System.out.println("\t1. Full time");
        System.out.println("\t2. Part time");
        int WTchoice = Integer.parseInt(scanner.nextLine());

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getTimeType().ordinal() == WTchoice) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    public void filterSalary (Scanner scanner) {
        System.out.println("Loc theo muc luong:");
        System.out.print("\tMuc luong toi thieu: ");
        int minSalary = Integer.parseInt(scanner.nextLine());
        System.out.print("\tMuc luong toi da: ");
        int maxSalary = Integer.parseInt(scanner.nextLine());

        List<Job> current = new ArrayList<>(displayedList); // copy displayedJob
        displayedList.clear();
        for (Job job : current) {
            if (job.getSalary() >= minSalary && job.getSalary() <= maxSalary) {
                displayedList.add(job);
            }
        }
        printFilteredJob();
    }

    private void printFilteredJob() {
        for (Job job : displayedList) {
            System.out.println(job.printBrief());
        }
    }

//    selectJob
    public void selectJob (Scanner scanner) {
        System.out.print("Nhap cong viec ban muon xem: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Job job = displayedList.get(choice);
        System.out.println(job.printDetail());
    }



}
