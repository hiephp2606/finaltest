package database;

import entities.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountData {
    public static Map<Integer, Account> accountMap = new HashMap<>();

    static {
        Account account = new Account("guru", "123", Account.Role.GURU, "Hiep", "26/06/2003", "123", "h", Account.AccountStatus.ACTIVE);
        accountMap.put(account.getId(), account);
    }

    public static void addAccount (Account a){
        accountMap.put(a.getId(), a);
    }

    public static Account getAccountById (int id) {
        return accountMap.get(id);
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
        for (Account a: accountMap.values()) {
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