package com.techelevator.dao;

import com.techelevator.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestDao {
    List<ServiceRequest> getServiceRequestsByManagerUsername(String username);

    List<ServiceRequest> getServiceRequestsByTenantUsername(String username);

    //ServiceRequest getServiceRequestById(int serviceRequestId);
    ServiceRequest getManagerServiceRequestById(int serviceRequestId, int managerId);

    ServiceRequest getTenantServiceRequestById(int serviceRequestId, int tenantId);

    //Status: Open, In Prog, Complete
    List<ServiceRequest> getManagerServiceRequestsByStatus(String status, int userId);

    List<ServiceRequest> getTenantServiceRequestsByStatus(String status, int userId);

    ServiceRequest createServiceRequest(ServiceRequest serviceRequest);

    ServiceRequest updateServiceRequest(ServiceRequest serviceRequest, int managerId);

    int deleteServiceRequestById(int serviceRequestId, int tenantId);

}
