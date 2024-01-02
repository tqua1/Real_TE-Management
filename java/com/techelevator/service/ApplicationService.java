package com.techelevator.service;

import com.techelevator.model.Application;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import java.security.Principal;
import java.util.List;

public interface ApplicationService {
    List<Application> viewAllApplications(Principal principal);
    List<Application> viewApplicationsByStatus(Principal principal, String status);
    List<Application> viewApplicationsByPropertyId(Principal principal, int propertyId);
    Application viewApplicationById(Principal principal, int applicationId);
    Application createApplication(Principal principal, Application application);
    Application approveOrRejectApplication(Principal principal, Application updatedApplication);
    void deleteApplication(Principal principal, int id);
}
