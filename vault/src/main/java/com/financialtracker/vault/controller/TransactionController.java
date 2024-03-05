package com.financialtracker.vault.controller;

import com.financialtracker.vault.dao.AccountDao;
import com.financialtracker.vault.dao.TransactionDao;
import com.financialtracker.vault.exception.ServiceException;
import com.financialtracker.vault.model.Account;
import com.financialtracker.vault.model.Transaction;
import com.financialtracker.vault.service.AccountService;
import com.financialtracker.vault.service.TransactionService;
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
public class TransactionController {
        private final TransactionService transactionService;
        private final TransactionDao transactionDao;
        public TransactionController(TransactionService transactionService, TransactionDao transactionDao) {
            this.transactionService = transactionService;
            this.transactionDao = transactionDao;
        }

        @PreAuthorize("hasRole('ROLE_USER')")
        @GetMapping("/transactions")
        public List<Transaction> getUserTransactions(@Valid Principal principal) {
            try{
                List<Transaction> transactions = transactionService.viewAllTransactions(principal);
                return transactions;
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }

        }

        @GetMapping("/transactions/{transactionId}")
        public Account getTransactionById(@Valid Principal principal, @PathVariable("transactionId") int transactionId) {
            try {
                if (transaction == null) {
                    throw new SecurityException("Transaction not found");
                }
                return transaction;
            }catch (SecurityException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }
        }


        @GetMapping("/transactions/{transactionId}")
        public  List<Transaction>  getTransactionsByType(@Valid Principal principal, @PathVariable("boolean") String type ) {
            try{
                List<Transaction> transactions = transactionService.viewTransactionsByType(principal, type);
                return transactions;
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }
        }

        @PostMapping("/transactions")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Transaction> sendTransaction(@Valid Principal principal, @RequestBody Transaction
                newTransaction){
            Transaction createdTransaction= null;
            try{
                createdTransaction = transactionService.createTransaction(principal,newTransaction);
                if(createdTransaction == null){
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    return ResponseEntity.ok(createdTransaction);
                }
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("transactions/update/{transactionId}")
        public Transaction updateTransaction(@Valid Principal principal, @RequestBody Transaction transaction, @PathVariable("transactionId") int transactionId) {
            transaction.setTransactionId(transactionId);
            try{
                Transaction updateTransaction = TransactionService.updateTransaction(principal,transaction);
                if(updateTransaction == null){
                    throw new ServiceException("Transaction not found ");
                }
                return updateTransaction;
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
            }
        }

        @PreAuthorize("isAuthenticated")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/transactions/{transactionId}")
        public void deleteTransaction(@PathVariable int transactionId, Principal principal) {
            try{
                transactionService.deleteTransaction(principal, transactionId );
            } catch (SecurityException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            }
        }
}
