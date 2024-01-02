package com.techelevator.service;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.RentTransactionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Authority;
import com.techelevator.model.RentTransaction;
import com.techelevator.model.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RentTransactionServiceImpl implements RentTransactionService{
    private RentTransactionDao rentTransactionDao;
    private UserDao userDao;
    private PropertyDao propertyDao;

    public RentTransactionServiceImpl(RentTransactionDao rentTransactionDao, UserDao userDao, PropertyDao propertyDao) {
        this.rentTransactionDao = rentTransactionDao;
        this.userDao = userDao;
        this.propertyDao = propertyDao;
    }

    @Override
    public List<RentTransaction> viewAllRentTransactions(Principal principal) {
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        Authority managerRole = new Authority("ROLE_ADMIN");
        Authority tenantRole = new Authority("ROLE_USER");
        List<RentTransaction> rentTransactions = null;
        try{
            if(loggedInUser.getAuthorities().contains(managerRole)){
                rentTransactions = rentTransactionDao.getRentTransactionsByManagerUsername(principal.getName());
            } else if (loggedInUser.getAuthorities().contains(tenantRole)) {
                rentTransactions = rentTransactionDao.getRentTransactionsByTenantUsername(principal.getName());
            }
            return rentTransactions;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public List<RentTransaction> viewRentTransactionsByPropertyId(Principal principal, int propertyId) {
        List<RentTransaction> rentTransactions = null;
        try{
            rentTransactions = rentTransactionDao.getRentTransactionsByPropertyId(principal.getName(),propertyId);
            return rentTransactions;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public List<RentTransaction> viewRentTransactionsByPastDue(Principal principal, boolean isPastDue) {
        List<RentTransaction> rentTransactions = null;
        try{
            rentTransactions = rentTransactionDao.getRentTransactionsPastDue(principal.getName(),isPastDue);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
        return rentTransactions;
    }

    @Override
    public RentTransaction viewRentTransactionById(Principal principal, int rentTransactionId) {
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        Authority managerRole = new Authority("ROLE_ADMIN");
        Authority tenantRole = new Authority("ROLE_USER");
        RentTransaction rentTransaction = null;
        try{
            if(rentTransactionId <= 7000){
                throw new DaoException("Cannot find an rent transaction with id provided.");
            } else {
                if(loggedInUser.getAuthorities().contains(managerRole)){
                    rentTransaction = rentTransactionDao.getRentTransactionByIdManager(principal.getName(),rentTransactionId);
                } else if (loggedInUser.getAuthorities().contains(tenantRole)){
                    rentTransaction = rentTransactionDao.getRentTransactionByIdTenant(principal.getName(), rentTransactionId);
                }
            }
            return rentTransaction;
        }catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public RentTransaction createRentTransaction(Principal principal, RentTransaction rentTransaction) {
        RentTransaction newRentTransaction;
        try{
            newRentTransaction = rentTransactionDao.createRentTransaction(rentTransaction);
            return newRentTransaction;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteRentTransaction(Principal principal, int id) {
        try{
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            int tenantId = userDao.getTenantIdFromUserId(userId);
            rentTransactionDao.deleteRentTransactionById(tenantId, id);
        }catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public RentTransaction updateRentTransaction(Principal principal, RentTransaction rentTransaction) {
//        List<RentTransaction> pastDueRent = viewRentTransactionsByPastDue(principal, true);
        RentTransaction updatedRentTransaction = null;
        try{
//            for(RentTransaction transaction : pastDueRent){
//                if(transaction.getTransactionId() == rentTransaction.getTransactionId()){
//                        transaction.setPastDue(false);
//                    updatedRentTransaction = rentTransactionDao.updateRentTransaction(transaction);
//                }
//            }
            updatedRentTransaction = rentTransactionDao.updateRentTransaction(rentTransaction);
            return updatedRentTransaction;
        }catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }

    }
}
