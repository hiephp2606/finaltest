package service;

import database.AccountData;
import entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    LoginService loginService;
    Scanner scanner = new Scanner(System.in);
    List<Account> inactiveAccount = new ArrayList<>();

//    Duyet tai khoan
    public void listInactiveAccount () {
        for (Account account : AccountData.getList()) {
            if (account.getAccountStatus().equals(Account.AccountStatus.INACTIVE)
                    && loginService.who.getRole().ordinal() < account.getRole().ordinal()) {
                inactiveAccount.add(account);
                System.out.println(account.getId() +". " + account.getUsername() + account.getRole());
            }
        }
    }


    public void approveAccount () {
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
                System.out.println("Nhap lua chon duyet tai khoan cua ban:");
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

//
}
