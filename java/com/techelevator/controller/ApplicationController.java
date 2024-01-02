package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Application;
import com.techelevator.service.ApplicationService;
import com.techelevator.service.ApplicationServiceImpl;
import com.techelevator.service.NotificationService;
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
public class ApplicationController {
    private ApplicationService applicationService;
    private NotificationService notificationService;

//    public ApplicationController(ApplicationService applicationService) {
//        this.applicationService = applicationService;
//    }

    public ApplicationController(ApplicationService applicationService, NotificationService notificationService) {
        this.applicationService = applicationService;
        this.notificationService = notificationService;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/applications/managing")
    public List<Application> getAllApplications(@Valid Principal principal) {
        try{
            List<Application> applications = applicationService.viewAllApplications(principal);
            return applications;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/applications/managing/{propertyId}")
    public List<Application> getAllApplicationsByPropertyId(@Valid Principal principal, @PathVariable("propertyId")
    int propertyId) {
        try{
            List<Application> applications = applicationService.viewApplicationsByPropertyId(principal, propertyId);
            return applications;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/applications")
    public List<Application> getMyApplications(@Valid Principal principal) {
        try{
            List<Application> applications = applicationService.viewAllApplications(principal);
            return applications;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/applications/status/{status}")
    public List<Application> getApplicationByStatus(@Valid Principal principal, @PathVariable("status") String status){
        try{
            List<Application> applications = applicationService.viewApplicationsByStatus(principal, status);
            return applications;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
    @GetMapping("/applications/{id}")
    public Application getApplicationById(@Valid Principal principal, @PathVariable("id") int applicationId){
        try{
            Application application = applicationService.viewApplicationById(principal, applicationId);
            if(application == null){
                throw new ServiceException("No Application found with ID: " + applicationId);
            }
            return application;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> addApplication(@Valid Principal principal, @RequestBody Application newApplication){
        Application createdApplication = null;
        try{
            createdApplication = applicationService.createApplication(principal, newApplication);
            if(createdApplication == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                notificationService.sendEmail();
                return ResponseEntity.ok(createdApplication);
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("applications/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication( @Valid Principal principal,@PathVariable("id") int applicationId){
        try{
            applicationService.deleteApplication(principal,applicationId);
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')") //TODO: send notification here ??????
    @PutMapping("/applications/update/{id}")
    public Application updateApplication(@Valid Principal principal, @RequestBody Application application, @PathVariable("id") int applicationId){
        application.setApplicationId(applicationId);
        try{
            Application updatedApplication = applicationService.approveOrRejectApplication(principal, application);
            if(updatedApplication == null){
                throw new ServiceException("No Application found with ID: " + applicationId);
            }
            return updatedApplication;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }
}

