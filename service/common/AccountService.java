package service.common;

import database.AccountData;
import entities.Account;
import utils.InputUtils;

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
        int chooseAccount = InputUtils.loopInputInteger("Nhap id tai khoan ban muon duyet: ", scanner);
        Account account = AccountData.getAccountById(chooseAccount);

        int choiceActionAccount = InputUtils.loopInputChoice(
                List.of(
                        "Lua chon hanh dong cua ban: ",
                        "\t 1. Xem thong tin tai khoan",
                        "\t 2. Duyet tai khoan"
                ),
                scanner,
                1,
                2);

         Integer.parseInt(scanner.nextLine());

        switch (choiceActionAccount) {
            case 1:
                System.out.println(account);
                break;
            case 2:
                int approveChoice = InputUtils.loopInputChoice(
                    List.of(
                            "Nhap lua chon duyet tai khoan cua ban:",
                            "\t 1. Active",
                            "\t 2. Decline"
                    ),
                    scanner,
                    1,
                    2);

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

// Cap nhat
    public void updateEmail () {

    }
}
