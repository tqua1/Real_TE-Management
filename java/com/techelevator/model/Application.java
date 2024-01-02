package com.techelevator.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Application {

    private int applicationId;
    @NotEmpty( message = "The field 'userId' is required.")
    private int userId;
    @NotEmpty( message = "The field 'propertyId' is required.")
    private int propertyId;
    @NotEmpty( message = "The field 'status' is required.")
    //Status: STATUS_PENDING, STATUS_REJECTED, STATUS_APPROVED
    private String status;
    @NotEmpty( message = "The field 'fullName' is required.")
    private String fullName;
    @NotEmpty( message = "The field 'email' is required.")
    private String email;
    @NotEmpty( message = "The field 'isAbove18' is required.")
    private boolean isAbove18;
    @NotEmpty( message = "The field 'hasRoomates' is required.")
    private boolean hasRoomates;
    private String roomateNames;

    public Application() {}

    public Application(int applicationId, int userId, int propertyId, String status, String fullName, String email, boolean isAbove18, boolean hasRoomates, String roomateNames) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.propertyId = propertyId;
        this.status = status;
        this.fullName = fullName;
        this.email = email;
        this.isAbove18 = isAbove18;
        this.hasRoomates = hasRoomates;
        this.roomateNames = roomateNames;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAbove18() {
        return isAbove18;
    }

    public void setAbove18(boolean above18) {
        isAbove18 = above18;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHasRoomates() {
        return hasRoomates;
    }

    public void setHasRoomates(boolean hasRoomates) {
        this.hasRoomates = hasRoomates;
    }

    public String getRoomateNames() {
        return roomateNames;
    }

    public void setRoomateNames(String roomateNames) {
        this.roomateNames = roomateNames;
    }
}
