package com.techelevator.service;

import com.techelevator.model.Application;
import com.techelevator.model.RentTransaction;

import java.security.Principal;
import java.util.List;

public interface RentTransactionService {
    List<RentTransaction> viewAllRentTransactions(Principal principal);
    List<RentTransaction> viewRentTransactionsByPastDue(Principal principal, boolean isPastDue);
    List<RentTransaction> viewRentTransactionsByPropertyId(Principal principal, int propertyId);
    RentTransaction viewRentTransactionById(Principal principal, int rentTransactionId);
    RentTransaction createRentTransaction(Principal principal, RentTransaction rentTransaction);
    void deleteRentTransaction(Principal principal, int id);
    RentTransaction updateRentTransaction(Principal principal, RentTransaction rentTransaction);
}
