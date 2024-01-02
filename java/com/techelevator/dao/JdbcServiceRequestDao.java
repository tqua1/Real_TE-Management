package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Authority;
import com.techelevator.model.ServiceRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcServiceRequestDao implements ServiceRequestDao {

    private JdbcTemplate jdbcTemplate;

    private List<ServiceRequest> serviceRequests = new ArrayList<>();

    public JdbcServiceRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //ADMIN accessing service requests to their properties
    public List<ServiceRequest> getServiceRequestsByManagerUsername(String username) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        String sql = "SELECT sr.service_request_id, sr.tenant_id, sr.request_details, sr.status " +
                "FROM service_requests sr " +
                "JOIN tenant_profiles tp ON sr.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                serviceRequests.add(mapRowToServiceRequest(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequests;
    }

    @Override //TENANT accessing their own service requests
    public List<ServiceRequest> getServiceRequestsByTenantUsername(String username) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        String sql = "SELECT sr.service_request_id, sr.tenant_id, sr.request_details, sr.status " +
                "FROM service_requests sr " +
                "JOIN tenant_profiles tp ON sr.tenant_id = tp.tenant_id " +
                "JOIN users u ON tp.user_id = u.user_id " +
                "WHERE u.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                serviceRequests.add(mapRowToServiceRequest(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequests;
    }

    @Override
    public ServiceRequest getManagerServiceRequestById(int serviceRequestId, int managerId) {
        ServiceRequest serviceRequest = null;
        String sql = "SELECT sr.service_request_id, sr.tenant_id, sr.request_details, sr.status " +
                "FROM service_requests sr " +
                "JOIN tenant_profiles tp ON sr.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "WHERE service_request_id = ? AND manager_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, serviceRequestId, managerId);
            if (results.next()) {
                serviceRequest = mapRowToServiceRequest(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequest;
    }

    @Override
    public ServiceRequest getTenantServiceRequestById(int serviceRequestId, int tenantId) {
        ServiceRequest serviceRequest = null;
        String sql = "SELECT service_request_id, tenant_id, request_details, status " +
                "FROM service_requests " +
                "WHERE service_request_id = ? AND tenant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, serviceRequestId, tenantId);
            if (results.next()) {
                serviceRequest = mapRowToServiceRequest(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequest;
    }

    //Status: STATUS_OPEN, STATUS_IN_PROGRESS, STATUS_COMPLETE

    // users (tenants) will see their requests by status
    // admin will see all requests related to their properties
    @Override
    public List<ServiceRequest> getManagerServiceRequestsByStatus(String status, int id) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        String sql = "SELECT sr.service_request_id, sr.tenant_id, sr.request_details, sr.status " +
                "FROM service_requests sr " +
                "JOIN tenant_profiles tp ON sr.tenant_id = tp.tenant_id " +
                "JOIN properties p ON tp.property_id = p.property_id " +
                "WHERE status = ? AND manager_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, status, id);
            while (results.next()) {
                serviceRequests.add(mapRowToServiceRequest(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequests;
    }

    @Override
    public List<ServiceRequest> getTenantServiceRequestsByStatus(String status, int id) {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        String sql = "SELECT service_request_id, tenant_id, request_details, status " +
                "FROM service_requests " +
                "WHERE status = ? AND tenant_id = ?;";
        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, status, id);
            while (results.next()) {
                serviceRequests.add(mapRowToServiceRequest(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return serviceRequests;
    }

    @Override
    //Status: STATUS_OPEN, STATUS_IN_PROGRESS, STATUS_COMPLETE
    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        ServiceRequest request = null;
        String sql = "INSERT INTO service_requests (tenant_id, request_details, status) " +
                "VALUES (?, ?, ?) RETURNING service_request_id;";
        try {
            int requestId = jdbcTemplate.queryForObject(sql, int.class, serviceRequest.getTenantId() , serviceRequest.getRequestDetails(), serviceRequest.getStatus());
            return getTenantServiceRequestById(requestId, serviceRequest.getTenantId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public ServiceRequest updateServiceRequest(ServiceRequest serviceRequest, int managerId) {
        ServiceRequest updatedServiceRequest = null;
        String sql = "UPDATE service_requests " +
                "SET tenant_id = ?, request_details = ?, status = ? " +
                "WHERE service_request_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, serviceRequest.getTenantId(), serviceRequest.getRequestDetails(), serviceRequest.getStatus(), serviceRequest.getServiceRequestId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedServiceRequest = getManagerServiceRequestById(serviceRequest.getServiceRequestId(), managerId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedServiceRequest;
    }

    @Override
    public int deleteServiceRequestById(int serviceRequestId, int tenantId) {
        int rowsAffected;
        String sql = "DELETE FROM service_requests WHERE service_request_id = ? AND tenant_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, serviceRequestId, tenantId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    private ServiceRequest mapRowToServiceRequest(SqlRowSet results) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setServiceRequestId(results.getInt("service_request_id"));
        serviceRequest.setTenantId(results.getInt("tenant_id"));
        serviceRequest.setRequestDetails(results.getString("request_details"));
        serviceRequest.setStatus(results.getString("status"));
        return serviceRequest;
    }
}
