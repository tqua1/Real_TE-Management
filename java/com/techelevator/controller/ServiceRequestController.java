package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.ServiceRequest;
import com.techelevator.service.ServiceRequestService;
import com.techelevator.service.ServiceRequestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class ServiceRequestController {
    private ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    /**
     *
     * @param principal
     * @return the service requests a user has made
     */
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/service-requests")
    public List<ServiceRequest> getAllMyServiceRequests(@Valid Principal principal){
        try{
            List<ServiceRequest> serviceRequests = serviceRequestService.viewAllServiceRequests(principal);
            return serviceRequests;
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param serviceRequestId
     * @return a service request by service request id
     */
    @GetMapping("/service-requests/{id}")
    public ServiceRequest getServiceRequestById(@Valid Principal principal, @PathVariable("id") int serviceRequestId){
        try{
            ServiceRequest serviceRequest = serviceRequestService.viewServiceRequestById(principal, serviceRequestId);
            if(serviceRequest == null){
                throw new ServiceException("No service request found with ID: " + serviceRequestId);
            }
            return serviceRequest;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param status
     * @return service requests by status
     */
    @GetMapping("/service-requests/status/{status}")
    public List<ServiceRequest> getServiceRequestsByStatus(@Valid Principal principal, @PathVariable String status){
        try{
            List<ServiceRequest> serviceRequests = serviceRequestService.viewServiceRequestsByStatus(principal, status);
            return serviceRequests;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     * creates a new service request
     *
     * @param principal
     * @param newServiceRequest
     * @return a new service request
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/service-requests")
    @ResponseStatus(HttpStatus.CREATED) //Status: Open
    public ResponseEntity<ServiceRequest> addServiceRequest(@Valid @RequestBody ServiceRequest newServiceRequest, Principal principal){
        ServiceRequest createdServiceRequest = null;
        try{
            createdServiceRequest = serviceRequestService.createServiceRequest(principal, newServiceRequest);
            if(createdServiceRequest == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                //TODO send notification here/after creating a service request as a confirmation email to the user that
                // it was created ADD other methods for logic to the notification service layer
                return ResponseEntity.ok(createdServiceRequest);
            }
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     * manager can update a service request
     *
     * @param principal
     * @param serviceRequest
     * @param id
     * @return the updated service request
     */

    //THIS ONE SAYS server error encountered
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/service-requests/{id}") //Status: In Progress
    public ServiceRequest updateServiceRequestStatus(@Valid Principal principal, @RequestBody ServiceRequest serviceRequest, @PathVariable int id){
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client side error - make sure your request is properly defined.");
        }
        serviceRequest.setServiceRequestId(id);
        try {
            ServiceRequest updatedServiceRequest = serviceRequestService.updateServiceRequest(serviceRequest, principal);
            if (updatedServiceRequest == null){
                throw new ServiceException("No service request found with ID: " + id);
            } else {
                // TODO SEND A NOTIFICATION OUT WHEN THE STATUS IS UPDATED TO STATUS COMPLETE
                return updatedServiceRequest;
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/service-requests/{id}")
    public  void deleteServiceRequest(@PathVariable int id, Principal principal) {
       try {
           serviceRequestService.withdrawServiceRequest(id, principal);
       } catch (ServiceException e) {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
       }
    }
}
