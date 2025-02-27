package service.byrole;

import database.AccountData;
import database.JobData;
import entities.Account;
import entities.Job;
import service.common.LoginService;
import service.common.RegisterService;
import service.intf.*;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService implements ApproveAccount, DeleteAccount, ApproveJob, DeletePost, UpdatePassword, UpdateEmail, UpdatePhonenumber {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);
    List<Account> activeAccount = new ArrayList<>();
    List<Account> inactiveAccount = new ArrayList<>();
    List<Job> activeJob = new ArrayList<>();
    List<Job> inactiveJob = new ArrayList<>();
    RegisterService registerService = new RegisterService();

    public AdminService(LoginService loginService, Scanner scanner) {
        this.loginService = loginService;
        this.scanner = scanner;
    }

    //    Duyet tai khoan
    public void listInactiveAccount () {
        for (Account account : AccountData.getList()) {
                if (account.getAccountStatus().equals(Account.AccountStatus.INACTIVE)
                        && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                    inactiveAccount.add(account);
                    System.out.println(account.getId() +". " + account.getUsername() +" - " + account.getRole());
                }
        }

    }


    public void approveAccountService () {
        boolean loop1 = true;
        boolean loop2 = true;
        int chooseAccount = InputUtils.loopInputInteger("Nhap ID: ", scanner);
        Account account = AccountData.getAccountById(chooseAccount);
        do {
            System.out.println("Lua chon hanh dong cua ban: ");
            System.out.println("\t 1. Xem thong tin tai khoan");
            System.out.println("\t 2. Duyet tai khoan");
            System.out.println("\t 3. Exit");

            int choiceActionAccount = InputUtils.loopInputInteger("Nhap lua chon cua ban: ", scanner);
            switch (choiceActionAccount) {
                case 1:
                    System.out.println(account);
                    break;
                case 2:

                    System.out.println("Lua chon duyet tai khoan cua ban [activate/ decline/ Exit]:");
                    System.out.println("\t 1. Active");
                    System.out.println("\t 2. Decline");
                    System.out.println("\t 3. Exit");

                    System.out.print("Nhap lua chon cua ban: ");
                    int approveChoice = InputUtils.loopInputInteger("", scanner);

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
    public void listActiveAccount () {
        for (Account account : AccountData.getList()) {
            if (account.getAccountStatus().equals(Account.AccountStatus.ACTIVE)
                    && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                activeAccount.add(account);
                System.out.println(account.getId() +". " + account.getUsername()+ " - " + account.getRole());
            }
        }
    }

    public void removeAccount () {
        int chooseId = InputUtils.loopInputInteger("Nhap ID: ", scanner);
        for (Account account : AccountData.getList()) {
            if (chooseId == account.getId() && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                AccountData.removeAccountById(chooseId);
            }
        }
    }

    //    Duyet cong viec
    public void listInactiveJob () {
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.INACTIVE)) {
                inactiveJob.add(job);
                System.out.println(job.printDetail());
            }
        }

    }

    public void approveJobService () {

        int chooseIdJob = InputUtils.loopInputInteger("Nhap ID: ", scanner);

        for (Job job : inactiveJob) {
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
        for (Job job : JobData.getJobList()) {
            if (job.getJobStatus().equals(Job.JobStatus.INACTIVE)) {
                activeJob.add(job);
                System.out.println(job.printDetail());
            }
        }

        return activeJob;
    }

    public void removeJob () {
        int id = InputUtils.loopInputInteger("Nhap ID: ", scanner);
        for (Job job : activeJob) {
            if (id == job.getId()) {
                JobData.removeJob(id);
            }
        }
    }

//    updatePassword



    @Override
    public void approveAccount() {
        listInactiveAccount();
        if (!inactiveAccount.isEmpty()) {
            approveAccountService();
        }
        else {
            System.out.println("Khong co tai khoan nao hien tai can duyet");
        }
    }


    @Override
    public void approveJob() {
        listInactiveJob();
        if (!inactiveJob.isEmpty()) {
            approveJobService();
        }
        else {
            System.out.println("Hien tai khong co cong viec nao can duyet!");
        }

    }

    @Override
    public void deletePost() {
        listActiveJob();
        if (!activeJob.isEmpty()) {
            removeJob();
        }
        else {
            System.out.println("Hien tai khong co cong viec nao kha dung!");
        }

    }


    @Override
    public void deleteAccount() {
        listActiveAccount();
        if (!activeAccount.isEmpty()) {
            removeAccount();
        }
        else {
            System.out.println("Hien tai dang khong co tai khoan nao kha dung!");
        }

    }

    @Override
    public void updateEmail() {
        do {
            String email = InputUtils.loopInputEmail("Cap nhat email: ", scanner);
                if (registerService.checkEmail(email) != null) {

                }
                else {
                    AccountData.getAccountById(loginService.who.getId()).setEmail(email);
                    break;
                }
        } while (true);

    }

    @Override
    public void updatePassword() {
        String password = InputUtils.loopInputPassword("Cap nhat password", scanner);
        AccountData.getAccountById(loginService.who.getId()).setPassword(password);
    }

    @Override
    public void updatePhonenumber() {
        do {
            int phoneNumber = InputUtils.loopInputPhoneNumber("Cap nhat so dien thoai: ", scanner);
                if (registerService.checkPhoneNumber(phoneNumber) != null) {

                }
                else {
                    AccountData.getAccountById(loginService.who.getId()).setPhoneNumber(phoneNumber);
                    break;
                }
        } while (true);
    }
}
