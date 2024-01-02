package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServiceRequest {
    private int serviceRequestId;
    @NotNull( message = "The field 'tenantId' is required.")
    private int tenantId;
    @NotEmpty( message = "The field 'requestDetails' is required.")
    private String requestDetails;
    @NotEmpty( message = "The field 'status' is required.")
    //Status: STATUS_OPEN, STATUS_IN_PROGRESS, STATUS_COMPLETE
    private String status;

    public ServiceRequest() {}

    public ServiceRequest(int serviceRequestId, int tenantId, String requestDetails, String status) {
        this.serviceRequestId = serviceRequestId;
        this.tenantId = tenantId;
        this.requestDetails = requestDetails;
        this.status = status;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getServiceRequestId() {
        return serviceRequestId;
    }


    public void setServiceRequestId(int serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
