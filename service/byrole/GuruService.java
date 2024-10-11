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
    public void listInactiveAccount () {
        List<Account> inactiveAccount = new ArrayList<>();
        for (Account account : AccountData.getList()) {
            if (account.getAccountStatus().equals(Account.AccountStatus.INACTIVE)
                    && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                inactiveAccount.add(account);
                System.out.println(account.getId() +". " + account.getUsername() +" - " + account.getRole());
            }
        }
    }


    public void approveAccountService () {
        System.out.print("Nhap id tai khoan ban muon duyet: ");
        int chooseAccount = Integer.parseInt(scanner.nextLine());
        Account account = AccountData.getAccountById(chooseAccount);

        System.out.println("Lua chon hanh dong cua ban: ");
        System.out.println("\t 1. Xem thong tin tai khoan");
        System.out.println("\t 2. Duyet tai khoan");
        System.out.print("Nhap lua chon cua ban: ");

        int choiceActionAccount = Integer.parseInt(scanner.nextLine());

        switch (choiceActionAccount) {
            case 1:
                System.out.println(account);
                break;
            case 2:
                System.out.println("Nhap lua chon duyet tai khoan cua ban [activate/ decline]:");
                System.out.println("\t 1. Active");
                System.out.println("\t 2. Decline");

                int approveChoice = Integer.parseInt(scanner.nextLine());

                switch (approveChoice) {
                    case 1:
                        account.setAccountStatus(Account.AccountStatus.ACTIVE);
                        break;
                    case 2:
                        AccountData.removeAccountById(account.getId());
                        break;
                }
                break;
        }
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
        System.out.print("Nhap id tai khoan ban muon xoa: ");
        int chooseId = Integer.parseInt(scanner.nextLine());
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
        System.out.print("Nhap id cong viec ban muon duyet");
        int chooseIdJob = Integer.parseInt(scanner.nextLine());

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
            }
        }
    }

    //    Xoa cong viec
    public void removeJob () {
        List<Job> activeJob = new ArrayList<>();
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.ACTIVE)) {
                job.printDetail();
                activeJob.add(job);
            }
        }

        System.out.print("Nhap Id job can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Job job : activeJob) {
            if (id == job.getId()) {
                JobData.removeJob(id);
            }
        }
    }
    @Override
    public void approveAccount() {
        listInactiveAccount();
        approveAccountService();
    }


    @Override
    public void approveJob() {
        listInactiveJob();
        approveJobService();
    }

    @Override
    public void deletePost() {
        removeJob();
    }


    @Override
    public void deleteAccount() {
        listActiveAccount();
        removeAccount();
    }
}
