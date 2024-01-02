package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.Property;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPropertyDao implements PropertyDao {

    private final JdbcTemplate jdbcTemplate;

    private final List<Property> properties = new ArrayList<>();

    public JdbcPropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT property_id, manager_id, address, number_of_rooms, rent, is_available " +
                "FROM properties;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                properties.add(mapRowToProperty(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return properties;
    }

    @Override
    public Property getPropertyById(int propertyId) {
        Property property = null;
        String sql = "SELECT property_id, manager_id, address, number_of_rooms, rent, is_available " +
                "FROM properties " +
                "WHERE property_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
            if (results.next()) {
                property = mapRowToProperty(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return property;
    }


    @Override
    public List<Property> getPropertiesByTenantUsername(String username) {
        List<Property> properties = new ArrayList<>();
        String sql =
                "SELECT p.property_id, p.manager_id, p.address, p.number_of_rooms, p.rent, p.is_available " +
                        "FROM properties p " +
                        "JOIN tenant_profiles tp ON  p.property_id = tp.property_id " +
                        "JOIN users u ON tp.user_id = u.user_id " +
                        "WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                properties.add(mapRowToProperty(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return properties;
    }
    @Override
    public List<Property> getPropertiesByManagerUsername(String username) {
        List<Property> properties = new ArrayList<>();
        String sql1 =
                "SELECT p.property_id, p.manager_id, p.address, p.number_of_rooms, p.rent, p.is_available " +
                        "FROM properties p " +
                        "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                        "JOIN users u ON mp.user_id = u.user_id " +
                        "WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql1, username);
            while (results.next()) {
                properties.add(mapRowToProperty(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return properties;
    }

    @Override
    public Property createProperty(Property property, int managerId) {
        int newPropertyId;
        String sql = "INSERT INTO properties (manager_id, address, number_of_rooms, rent, is_available) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING property_id;";
        try {
            newPropertyId = jdbcTemplate.queryForObject(sql, int.class, managerId, property.getAddress(), property.getNumberOfRooms(), property.getRent(), property.isAvailable());

            return getPropertyById(newPropertyId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Property updateProperty(Property property, int managerId) {
        Property updatedProperty = new Property();
        String sql = "UPDATE properties SET manager_id = ?, address = ?, number_of_rooms = ?, rent = ?, is_available = ? " +
                "WHERE property_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, managerId, property.getAddress(),
                    property.getNumberOfRooms(), property.getRent(), property.isAvailable(), property.getPropertyId());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedProperty = getPropertyById(property.getPropertyId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedProperty;
    }


    @Override
    public int deleteProperty(int propertyId, int managerId) {
        int rowsAffected;
        String sql = "DELETE FROM properties WHERE property_id = ? AND manager_id = ?;";
        try {
            rowsAffected = jdbcTemplate.update(sql, propertyId, managerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }


    private Property mapRowToProperty(SqlRowSet results) {
        return new Property(results.getInt("property_id"), results.getInt("manager_id"), results.getString("address"), results.getInt("number_of_rooms"), results.getBigDecimal("rent"), results.getBoolean("is_available"));
    }

}
