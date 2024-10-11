package service.byrole;

import database.AccountData;
import database.JobData;
import entities.Account;
import entities.Job;
import service.LoginService;
import service.intf.ApproveAccount;
import service.intf.ApproveJob;
import service.intf.DeleteAccount;
import service.intf.DeletePost;
import ultis.Ultis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuruService implements ApproveAccount, DeleteAccount, ApproveJob, DeletePost {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);

    public GuruService(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    //    Duyet tai khoan
    public List<Account> listInactiveAccount () {
        List<Account> inactiveAccount = new ArrayList<>();
        for (Account account : AccountData.getList()) {
                if (account.getAccountStatus().equals(Account.AccountStatus.INACTIVE)
                        && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                    inactiveAccount.add(account);
                    System.out.println(account.getId() +". " + account.getUsername() +" - " + account.getRole());
                }
        }

        return inactiveAccount;
    }


    public void approveAccountService () {
        boolean loop1 = true;
        boolean loop2 = true;
        int chooseAccount = Ultis.inputId(scanner);
        Account account = AccountData.getAccountById(chooseAccount);
        do {
            System.out.println("Lua chon hanh dong cua ban: ");
            System.out.println("\t 1. Xem thong tin tai khoan");
            System.out.println("\t 2. Duyet tai khoan");
            System.out.println("\t 3. Exit");

            int choiceActionAccount = Ultis.inputInteger(scanner);
            switch (choiceActionAccount) {
                case 1:
                    System.out.println(account);
                    break;
                case 2:

                    System.out.println("Lua chon duyet tai khoan cua ban [activate/ decline/ Exit]:");
                    System.out.println("\t 1. Active");
                    System.out.println("\t 2. Decline");
                    System.out.println("\t 3. Exit");

                    int approveChoice = Ultis.inputInteger(scanner);

                    switch (approveChoice) {
                        case 1:
                            account.setAccountStatus(Account.AccountStatus.ACTIVE);
                            System.out.println("Kich hoat tai khoan thanh cong");
                            break;
                        case 2:
                            AccountData.removeAccountById(account.getId());
                            System.out.println("Da tu choi va xoa tai khoan");
                            break;
                        case 3:
                            break;
                    }
                    loop1 = false;
                    break;
                case 3:
                    loop1 = false;
                    break;
            }

        } while (loop1 == true);
    }

//    Xoa tai khoan
    public List listActiveAccount () {
        List<Account> activeAccount = new ArrayList<>();
        for (Account account : AccountData.getList()) {
            if (account.getAccountStatus().equals(Account.AccountStatus.ACTIVE)
                    && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                activeAccount.add(account);
                System.out.println(account.getId() +". " + account.getUsername() + account.getRole());
            }
        }
        return activeAccount;
    }

    public void removeAccount () {
        List<Account> listActivatedAccount = listActiveAccount();
        int chooseId = Ultis.inputId(scanner);
        for (Account account : listActivatedAccount) {
            if (chooseId == account.getId() && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                AccountData.removeAccountById(chooseId);
            }
        }

    }

    //    Duyet cong viec
    public List listInactiveJob () {
        List<Job> inActiveJob = new ArrayList<>();
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.INACTIVE)) {
                inActiveJob.add(job);
                job.printDetail();
            }
        }

        return inActiveJob;
    }

    public void approveJobService () {
        List<Job> listInactivatedJob = listInactiveJob();

        int chooseIdJob = Ultis.inputId(scanner);

        for (Job job : listInactivatedJob) {
            if (chooseIdJob == job.getId()) {
                System.out.print("Nhap lua chon [Duyet/ Tu choi]: ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Duyet")) {
                    job.setJobStatus(Job.JobStatus.ACTIVE);
                }
                else if (choice.equalsIgnoreCase("Tu choi")) {
                    job.setJobStatus(Job.JobStatus.DECLINE);
                }
                else {
                    System.out.println("Vui long vhi nhap 'Duyet' hoac 'Tu choi'");
                }
            }
        }
    }

    //    Xoa cong viec
    public List<Job> listActiveJob () {
        List<Job> activeJob = new ArrayList<>();
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.INACTIVE)) {
                activeJob.add(job);
                job.printDetail();
            }
        }

        return activeJob;
    }

    public void removeJob () {
        List<Job> activeJob = listActiveJob();
        int id = Ultis.inputId(scanner);
        for (Job job : activeJob) {
            if (id == job.getId()) {
                JobData.removeJob(id);
            }
        }
    }


    @Override
    public void approveAccount() {
        if (listInactiveAccount().size() != 0) {
            approveAccountService();
        }
        else {
            System.out.println("Khong co tai khoan nao hien tai can duyet");
        }
    }


    @Override
    public void approveJob() {
        if (listInactiveJob().size() != 0) {
            approveJobService();
        }
        else {
            System.out.println("Hien tai khong co cong viec nao can duyet!");
        }

    }

    @Override
    public void deletePost() {
        if (listActiveJob().size() != 0) {
            removeJob();
        }
        else {
            System.out.println("Hien tai khong co cong viec nao kha dung!");
        }

    }


    @Override
    public void deleteAccount() {
        if (listActiveAccount().size() != 0) {
            removeAccount();
        }
        else {
            System.out.println("Hien tai dang khong co tai khoan nao kha dung!");
        }

    }
}
