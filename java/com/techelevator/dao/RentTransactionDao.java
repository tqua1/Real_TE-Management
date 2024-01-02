package com.techelevator.dao;

import com.techelevator.model.RentTransaction;

import java.util.List;

public interface RentTransactionDao {

    List<RentTransaction> getRentTransactionsByManagerUsername(String username);
    List<RentTransaction> getRentTransactionsByTenantUsername(String username);

    RentTransaction getRentTransactionById(int transactionId);

    List<RentTransaction> getRentTransactionsPastDue(String username,boolean isPastDue);
    List<RentTransaction> getRentTransactionsByPropertyId(String username,int propertyId);
    RentTransaction getRentTransactionByIdManager(String username, int rentTransactionId);
    RentTransaction getRentTransactionByIdTenant(String username, int rentTransactionId);
    RentTransaction createRentTransaction(RentTransaction rentTransaction);

    RentTransaction updateRentTransaction(RentTransaction rentTransaction);

    int deleteRentTransactionById(int tenantId, int rentTransaction);
}
