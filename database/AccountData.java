package database;

import entities.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {
    public static Map<Integer, Account> accountMap = new HashMap<>();

    static {
        Account guru = new Account("guru", "Letitbe2606!", Account.Role.GURU, "Hiep", "26/06/2003", 123456789, "hoanghiep.hp2606@gmail.com", Account.AccountStatus.ACTIVE);
        Account admin1 = new Account("hiepAdmin1", "Letitbe2606!", Account.Role.ADMIN, "Hiep", "26/06/2003", 122456789, "hoanghiep.hp26061@gmail.com", Account.AccountStatus.ACTIVE);
        Account admin2 = new Account("hiepAdmin2", "Letitbe2606!", Account.Role.ADMIN, "Hiep", "26/06/2003", 123356789, "hoanghiep.hp26062@gmail.com", Account.AccountStatus.INACTIVE);
        Account poster1 = new Account("hiepPoster1", "Letitbe2606!", Account.Role.POSTER, "Hiep", "26/06/2003", 123446789, "hoanghiep.hp26063@gmail.com", Account.AccountStatus.ACTIVE);
        Account poster2 = new Account("hiepPoster2", "Letitbe2606!", Account.Role.POSTER, "Hiep", "26/06/2003", 123455789, "hoanghiep.hp26064@gmail.com", Account.AccountStatus.INACTIVE);
        Account finder1 = new Account("hiepFinder1", "Letitbe2606!", Account.Role.FINDER, "Hiep", "26/06/2003", 123456689, "hoanghiep.hp26065@gmail.com", Account.AccountStatus.ACTIVE);
        Account finder2 = new Account("hiepFinder2", "Letitbe2606!1", Account.Role.FINDER, "Hiep", "26/06/2003", 123456779, "hoanghiep.hp26066@gmail.com", Account.AccountStatus.INACTIVE);

        accountMap.put(guru.getId(), guru);
        accountMap.put(admin1.getId(), admin1);
        accountMap.put(admin2.getId(), admin2);
        accountMap.put(poster1.getId(), poster1);
        accountMap.put(poster2.getId(), poster2);
        accountMap.put(finder1.getId(), finder1);
        accountMap.put(finder2.getId(), finder2);
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
        for (Account account: accountMap.values()) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }

        return null;
    }

    public static boolean matchPassword(Account account, String password) {
        return account.getPassword().equals(password);
    }
}