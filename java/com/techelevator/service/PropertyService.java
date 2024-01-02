package com.techelevator.service;

import com.techelevator.model.Property;

import java.security.Principal;
import java.util.List;

public interface PropertyService {

    List<Property> viewAllProperties();

    Property viewPropertyById(Principal principal, int propertyId);

    List<Property> viewPropertiesByUsername(Principal principal);

    Property createProperty(Principal principal, Property property);

    Property updateProperty(Property updatedProperty, Principal principal, int propertyId);

    void deleteProperty(Principal principal, int propertyId);



}
