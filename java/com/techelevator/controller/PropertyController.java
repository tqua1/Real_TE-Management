package com.techelevator.controller;

import com.techelevator.dao.PropertyDao;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Property;
import com.techelevator.service.PropertyService;
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
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyDao propertyDao;

    public PropertyController(PropertyService propertyService, PropertyDao propertyDao) {
        this.propertyService = propertyService;
        this.propertyDao = propertyDao;
    }

    /**
     * /properties

     * @return a list of properties that match the search parameters
     */
    @PreAuthorize("permitAll")
    @GetMapping("/properties")
    public List<Property> listProperties() {

        try {
            return propertyService.viewAllProperties();
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @param id the id of the property
     * @return all info for a given property
     */
    @PreAuthorize("permitAll")
    @GetMapping("/properties/{id}")
    public Property fetchProperty(@PathVariable int id, Principal principal) {
        try {
            return propertyService.viewPropertyById(principal,id);
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @return all properties managed by that user
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/properties/managing")
    public List<Property> fetchMyProperties(Principal principal) {
        try {
            return propertyService.viewPropertiesByUsername(principal);
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }
    //    As a property owner, I want to be able to see the data for my properties (average rent, vacancy%, etc.)

    /**
     *
     * @param newProperty
     * @param principal
     * @return the new property created
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/properties")
    public ResponseEntity<Property> addMyNewProperty(@Valid @RequestBody Property newProperty, Principal principal) {
        Property createdProperty = new Property();

        try {
           createdProperty = propertyService.createProperty(principal, newProperty);
            if (createdProperty == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return ResponseEntity.ok(createdProperty);
            }
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @param propertyToUpdateTo
     * @param id
     * @param principal
     * @return the updated property
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/properties/{id}")
    public Property updateMyProperty(@Valid @RequestBody Property propertyToUpdateTo, @PathVariable int id, Principal principal) {
        if (id <=0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client side error - make sure your request is properly defined.");
        }
        propertyToUpdateTo.setPropertyId(id);
        try {
            Property updatedProperty = propertyService.updateProperty(propertyToUpdateTo, principal, id);
            if (updatedProperty == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
            } else {
                return updatedProperty;
            }
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @param id
     * @param principal
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/properties/{id}")
    public void deleteMyProperty(@PathVariable int id, Principal principal) {
        try {
            propertyService.deleteProperty(principal, id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }


}
