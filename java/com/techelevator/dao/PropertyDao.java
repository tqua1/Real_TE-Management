package com.techelevator.dao;

import com.techelevator.model.Property;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface PropertyDao {

    List<Property> getProperties();

    Property getPropertyById(int propertyId);

    List<Property> getPropertiesByTenantUsername(String username);

    List<Property> getPropertiesByManagerUsername(String username);

    Property createProperty(Property property, int managerId);

    Property updateProperty(Property property, int managerId);

    int deleteProperty(int propertyId, int managerId);


}
