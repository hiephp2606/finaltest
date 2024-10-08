package database;

import entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountData {
    public static List<Account> accountList;

    static {
        accountList = new ArrayList<>();
        accountList.add(new Account("guru", "123", Account.Role.GURU, "Hiep", "26/06/2003", "123", "h"));
    }

    public static void addAccount (Account a){
        accountList.add(a);
    }

//    public static Account findAccount (String username, String password) {
//        Optional<Account> opt =
//                accountList.stream().filter(a -> {
////            ham boolean
//            return a.getUsername().equals(username) && a.getPassword().equals(password);
//        }).findAny();
//
//        return opt.get();
//    }

//    checkAccount
    public static Account matchUsername (String username) {
        for (Account a: accountList) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }

        return null;
    }

    public static boolean matchPassword(Account account, String password) {
        return account.getPassword().equals(password);
    }
}