package com.techelevator.service;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Authority;
import com.techelevator.model.Property;
import com.techelevator.model.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{

    private final PropertyDao propertyDao;

    private final UserDao userDao;

    public PropertyServiceImpl(PropertyDao propertyDao, UserDao userDao) {
        this.propertyDao = propertyDao;
        this.userDao = userDao;
    }

    @Override
    public List<Property> viewAllProperties() {
        try {
            return propertyDao.getProperties();
        } catch (DaoException e){
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Property viewPropertyById(Principal principal, int propertyId) {
        try {
            return propertyDao.getPropertyById(propertyId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Property> viewPropertiesByUsername(Principal principal) {
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        Authority managerRole = new Authority("ROLE_ADMIN");
        Authority tenantRole = new Authority("ROLE_USER");
        List<Property> properties = new ArrayList<>();
        try {
            if (loggedInUser.getAuthorities().contains(managerRole)) {
                properties = propertyDao.getPropertiesByManagerUsername(principal.getName());
            } else if (loggedInUser.getAuthorities().contains(tenantRole)) {
                properties = propertyDao.getPropertiesByTenantUsername(principal.getName());
            }
            return properties;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public Property createProperty(Principal principal, Property propertyToCreate) {
        Property newProperty = null;

        try {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            int managerId = userDao.getManagerIdFromUserId(userId);
            newProperty = propertyDao.createProperty(propertyToCreate, managerId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
        return newProperty;
    }

    @Override
    public Property updateProperty(@Valid Property propertyToUpdateTo, Principal principal, int propertyId) {
        try {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            int managerId = userDao.getManagerIdFromUserId(userId);
            Property updatedProperty = propertyDao.updateProperty(propertyToUpdateTo, managerId);
            return updatedProperty;
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteProperty(Principal principal, int propertyId){
        try {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            int managerId = userDao.getManagerIdFromUserId(userId);
            propertyDao.deleteProperty(propertyId, managerId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

}
