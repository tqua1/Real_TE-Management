package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RentTransaction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRentTransactionDao implements RentTransactionDao {

    private JdbcTemplate jdbcTemplate;

    private List<RentTransaction> rentTransactions = new ArrayList<>();

    public JdbcRentTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //ADMIN accessing rent transactions from their properties
    public List<RentTransaction> getRentTransactionsByManagerUsername(String username) {
        List<RentTransaction> rentTransactions = new ArrayList<>();
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt "+
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id " +
                "WHERE u.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                rentTransactions.add(mapRowToTransfer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rentTransactions;
    }

    @Override //TENANT accessing their own rent transactions
    public List<RentTransaction> getRentTransactionsByTenantUsername(String username) {
        List<RentTransaction> rentTransactions = new ArrayList<>();
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt " +
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN users u ON tp.user_id = u.user_id " +
                "WHERE u.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                rentTransactions.add(mapRowToTransfer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rentTransactions;
    }
    @Override //ADMIN would only access
    public List<RentTransaction> getRentTransactionsByPropertyId(String username,int propertyId) {
        List<RentTransaction> rentTransactions = new ArrayList<>();
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt "+
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ? AND p.property_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username,propertyId);
            while (results.next()) {
                RentTransaction rentTransaction = mapRowToTransfer(results);
                rentTransactions.add(rentTransaction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return rentTransactions;
    }

    @Override
    public RentTransaction getRentTransactionById(int transactionId) {
        RentTransaction rentTransaction = null;
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt " +
                "WHERE rt.transaction_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
            if (results.next()) {
                rentTransaction = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return rentTransaction;
    }

    @Override
    public List<RentTransaction> getRentTransactionsPastDue(String username,boolean isPastDue) {
        List<RentTransaction> rentTransactions = new ArrayList<>();
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt "+
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ? AND rt.past_due = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username,isPastDue);
            while (results.next()) {
                RentTransaction rentTransaction = mapRowToTransfer(results);
                rentTransactions.add(rentTransaction);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return rentTransactions;
    }

    @Override
    public RentTransaction getRentTransactionByIdManager(String username, int rentTransactionId) {
        RentTransaction rentTransaction = null;
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt "+
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ? AND rt.transaction_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username, rentTransactionId);
            if(result.next()){
                rentTransaction = mapRowToTransfer(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return rentTransaction;
    }

    @Override
    public RentTransaction getRentTransactionByIdTenant(String username, int rentTransactionId) {
        RentTransaction rentTransaction = null;
        String sql = "SELECT rt.transaction_id, rt.tenant_id, rt.amount, rt.due_date, rt.past_due " +
                "FROM rent_transactions rt " +
                "JOIN tenant_profiles tp ON rt.tenant_id = tp.tenant_id " +
                "JOIN users u ON tp.user_id = u.user_id " +
                "WHERE u.username = ? AND rt.transaction_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username, rentTransactionId);
            if(result.next()){
                rentTransaction = mapRowToTransfer(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return rentTransaction;
    }

    @Override
    public RentTransaction createRentTransaction(RentTransaction rentTransaction) {
        RentTransaction newRentTransaction = null;
        String sql = "INSERT INTO rent_transactions (tenant_id, amount, due_date, past_due) " +
                "VALUES (?, ?, ?, ?) RETURNING transaction_id;";
        try {
            int newRentTransactionId = jdbcTemplate.queryForObject(sql, int.class, rentTransaction.getTenantId(),
                    rentTransaction.getAmount(), rentTransaction.getDueDate(), rentTransaction.isPastDue());
            newRentTransaction = getRentTransactionById(newRentTransactionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newRentTransaction;
    }

    @Override
    public RentTransaction updateRentTransaction(RentTransaction rentTransaction) {
        RentTransaction updatedRentTransaction = null;
//        String sql = "UPDATE rent_transactions SET tenant_id = ?, amount = ?, due_date = ?, past_due = ? " +
//                "WHERE transaction_id = ?;";
        String sql = "UPDATE rent_transactions SET amount = ?, past_due = ? " +
                "WHERE transaction_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, rentTransaction.getAmount(),
                    rentTransaction.isPastDue(), rentTransaction.getTransactionId());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedRentTransaction = getRentTransactionById(rentTransaction.getTransactionId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedRentTransaction;
    }

    @Override
    public int deleteRentTransactionById(int tenantId, int transactionId) {
        int rowsAffected;
        String sql = "DELETE FROM rent_transactions rt WHERE rt.tenant_id = ? AND transaction_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, tenantId, transactionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    private RentTransaction mapRowToTransfer(SqlRowSet results) {
        RentTransaction rentTransaction = new RentTransaction();
        rentTransaction.setTransactionId(results.getInt("transaction_id"));
        rentTransaction.setTenantId(results.getInt("tenant_id"));
        rentTransaction.setAmount(results.getBigDecimal("amount"));
        rentTransaction.setDueDate(results.getDate("due_date"));
        rentTransaction.setPastDue(results.getBoolean("past_due"));
        return rentTransaction;
    }
}
