package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Property {


    private int propertyId;
    @NotNull ( message = "The field 'managerId' is required.")
    private int managerId;
    @NotNull( message = "The field 'address' is required.")
    private String address;
    @Min( value = 1, message = "The minimum number of rooms is 1.")
    private int numberOfRooms;

    @NotNull( message = "The field 'rent' is required.")
    private BigDecimal rent;

    @JsonProperty("available")
    @NotNull( message = "The field 'isAvailable' is required.")
    private boolean isAvailable;



    public Property() {}

    public Property(int propertyId, int managerId, String address, int numberOfRooms, BigDecimal rent, boolean isAvailable) {
        this.propertyId = propertyId;
        this.managerId = managerId;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rent = rent;
        this.isAvailable = isAvailable;

    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int manager_id) {
        this.managerId = managerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public String toString() {
        return "\n--------------------------------------------" +
                "\n Property Details" +
                "\n--------------------------------------------" +
                "\n Property Id: " + propertyId +
                "\n Manager Id: " + managerId +
                "\n Address:'" + address + '\'' +
                "\n Number of rooms: " + numberOfRooms +
                "\n Rent: " + rent +
                "\n Available: " + isAvailable;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return propertyId == property.propertyId && managerId == property.managerId && address.equals(property.address) && numberOfRooms == property.numberOfRooms && rent.compareTo(property.rent) == 0 && isAvailable == property.isAvailable;
    }
    @Override
    public int hashCode() {
        return Objects.hash(propertyId, managerId, address, numberOfRooms, rent, isAvailable);
    }
}
