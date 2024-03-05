package com.financialtracker.vault.controller;

import com.financialtracker.vault.dao.AccountDao;
import com.financialtracker.vault.exception.ServiceException;
import com.financialtracker.vault.model.Account;
import com.financialtracker.vault.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;
    private final AccountDao accountDao;
     public AccountController(AccountService accountService, AccountDao accountDao) {
         this.accountService = accountService;
         this.accountDao = accountDao;
     }

     @PreAuthorize("hasRole('ROLE_USER')")
     @GetMapping("/accounts")
     public List<Account> getUserAccounts(@Valid Principal principal) {
         try{
             List<Account> accounts = accountService.viewAllAcounts(principal);
             return accounts;
         }catch (ServiceException e) {
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
         }

    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccountById(@Valid Principal principal, @PathVariable("accountId") int accountId) {
         try {
             Account account = accountService.viewAccountById(principal, accountId);
             if (account == null) {
                 throw new SecurityException("Account not found");
             }
             return account;
         }catch (SecurityException e) {
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
         }
    }


    @GetMapping("/accounts/{accountId}")
    public  List<Account>  getDebtAccounts(@Valid Principal principal, @PathVariable("boolean") boolean isDebtAccount) {
        try{
            List<Account> accounts = accountService.viewDebtAccounts(principal, isDebtAccount);
            return accounts;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> sendAccount(@Valid Principal principal, @RequestBody Account
            newAccount){
        Account createdAccount= null;
        try{
            createdAccount = accountService.createAccount(principal,newAccount);
            if(createdAccount == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return ResponseEntity.ok(createdAccount);
            }
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("accounts/update/{accountId}")
    public Account updateAccount(@Valid Principal principal, @RequestBody Account account, @PathVariable("accountId") int accountId) {
         account.setAccountId(accountId);
        try{
            Account updatedAccount = AccountService.updateAccount(principal,account);
            if(updatedAccount == null){
                throw new ServiceException();
            }
            return updatedAccount;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

     @PreAuthorize("isAuthenticated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/accounts/{accountId}")
    public void deleteAccount(@PathVariable int accountId, Principal principal) {
         try{
             accountService.deleteaccount(principal, accountId );
         } catch (SecurityException e) {
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
         }
     }
}