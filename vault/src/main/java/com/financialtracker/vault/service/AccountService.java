package com.financialtracker.vault.service;

import com.financialtracker.vault.model.Account;

import java.security.Principal;
import java.util.List;

public interface AccountService {

    Account viewAccountById(Principal principal, int accountId);
    List<Account> viewAllAcounts(Principal principal);
    List<Account> viewDebtAccounts(Principal principal, boolean isDebtAccount);
    Account createAccount(Principal principal, Account newAccount);
    Account updateAccount(Principal principal, Account account);
    void deleteaccount(Principal principal, int accountId);
}
