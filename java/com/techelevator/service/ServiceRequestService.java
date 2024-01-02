package com.techelevator.service;

import com.techelevator.model.ServiceRequest;

import java.security.Principal;
import java.util.List;

public interface ServiceRequestService {
    public List<ServiceRequest> viewAllServiceRequests(Principal principal);

    public List<ServiceRequest> viewServiceRequestsByStatus(Principal principal, String status);

    public ServiceRequest viewServiceRequestById(Principal principal, int serviceRequestId);

    public ServiceRequest createServiceRequest(Principal principal, ServiceRequest serviceRequest);

    public ServiceRequest updateServiceRequest(ServiceRequest serviceRequest, Principal principal);

    void withdrawServiceRequest(int serviceRequestId, Principal principal);
}
