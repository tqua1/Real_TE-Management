package com.techelevator.service;

import com.techelevator.dao.ApplicationDao;
import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Application;
import com.techelevator.model.Authority;
import com.techelevator.model.Property;
import com.techelevator.model.User;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    private ApplicationDao applicationDao;
    private UserDao userDao;
    private PropertyDao propertyDao;

    public ApplicationServiceImpl(ApplicationDao applicationDao, UserDao userDao, PropertyDao propertyDao) {
        this.applicationDao = applicationDao;
        this.userDao = userDao;
        this.propertyDao = propertyDao;
    }

    @Override
    public List<Application> viewAllApplications(Principal principal) {
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        Authority managerRole = new Authority("ROLE_ADMIN");
        Authority userRole = new Authority("ROLE_USER");
        List<Application> applications = null;
        try{
            if(loggedInUser.getAuthorities().contains(managerRole)){
                applications = applicationDao.getApplicationsByManagerUsername(principal.getName());
            } else if (loggedInUser.getAuthorities().contains(userRole)) {
                applications = applicationDao.getApplicationsByUsername(principal.getName());
            }
            return applications;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public List<Application> viewApplicationsByStatus(Principal principal, String status) {
        List<Application> applications = null;
        try{
            applications = applicationDao.getApplicationsByStatus(principal.getName(),status);
            return applications;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public List<Application> viewApplicationsByPropertyId(Principal principal, int propertyId) {
        List<Application> applications = null;
        try{
            applications = applicationDao.getApplicationsByPropertyId(principal.getName(),propertyId);
            return applications;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public Application viewApplicationById(Principal principal, int applicationId) {
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        Authority managerRole = new Authority("ROLE_ADMIN");
        Authority userRole = new Authority("ROLE_USER");
        Application application = null;
        try {
            if(applicationId <= 5000){
                throw new DaoException("Cannot find an application with id provided.");
            } else{
                if(loggedInUser.getAuthorities().contains(managerRole)){
                    application = applicationDao.getApplicationByIdManager(principal.getName(), applicationId);
                } else if (loggedInUser.getAuthorities().contains(userRole)) {
                    application = applicationDao.getApplicationByIdUsername(principal.getName(),applicationId);
                }
            }
            return application;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Application createApplication(Principal principal, Application application) {
        Application newApplication;
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        try{
            application.setUserId(loggedInUser.getId());
            newApplication = applicationDao.createApplication(application);;
            return newApplication;
        }catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Application approveOrRejectApplication(Principal principal, Application updatedApplication) {
        List<Application> pendingApps = viewApplicationsByStatus(principal,"STATUS_PENDING");
        Application updatedApp = null;
        try{
            for(Application app : pendingApps){
                if(app.getApplicationId() == updatedApplication.getApplicationId()){
                    if(updatedApplication.getStatus().toUpperCase().contains("STATUS_APPROVED")){
                        app.setStatus("STATUS_APPROVED");
                    } else if (updatedApplication.getStatus().toUpperCase().contains("STATUS_REJECTED")){
                        app.setStatus("STATUS_REJECTED");
                    }
                    updatedApp = applicationDao.updateApplication(app);
                }
            }
            return updatedApp;
        }catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
    @Override
    public void deleteApplication(Principal principal,int id) {
        try{
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            applicationDao.deleteApplication(userId, id);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }
}
