package com.techelevator.dao;

import com.techelevator.model.Application;

import java.util.List;

public interface ApplicationDao {
    List<Application> getApplicationsByManagerUsername(String username);
    List<Application> getApplicationsByUsername(String username);
    List<Application> getApplicationsByPropertyId(String username, int propertyId);

    Application getApplicationByIdManager(String username, int applicationId);
    Application getApplicationByIdUsername(String username, int applicationId);
    Application getApplicationById(int applicationId);

    //Status: Pending, Rejected, Withdrawn
    List<Application> getApplicationsByStatus(String username,String status);

    Application createApplication(Application application);

    Application updateApplication(Application application);

    int deleteApplication(int userId,int applicationId);

}
