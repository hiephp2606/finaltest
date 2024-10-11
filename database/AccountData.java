package database;

import entities.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {
    public static Map<Integer, Account> accountMap = new HashMap<>();

    static {
        Account guru = new Account("guru", "123", Account.Role.GURU, "Hiep", "26/06/2003", 123, "h", Account.AccountStatus.ACTIVE);
        Account admin = new Account("hiep", "123", Account.Role.ADMIN, "Hiep", "26/06/2003", 123, "h", Account.AccountStatus.ACTIVE);
        Account poster = new Account("duy", "123", Account.Role.POSTER, "Duy", "26/06/2003", 123, "h", Account.AccountStatus.INACTIVE);
        Account finder = new Account("shit", "123", Account.Role.FINDER, "Shit", "26/06/2003", 123, "h", Account.AccountStatus.INACTIVE);

        accountMap.put(guru.getId(), guru);
        accountMap.put(admin.getId(), admin);
        accountMap.put(poster.getId(), poster);
        accountMap.put(finder.getId(), finder);
    }

    public static List<Account> getList () {
        return new ArrayList<>(accountMap.values());
    }

    public static void addAccount (Account a){
        accountMap.put(a.getId(), a);
    }

    public static Account getAccountById (int id) {
        return accountMap.get(id);
    }

    public static void removeAccountById(int id) {
        accountMap.remove(id);
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