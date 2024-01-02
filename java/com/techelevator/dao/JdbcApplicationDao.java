package com.techelevator.dao;

import com.techelevator.model.Application;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcApplicationDao implements ApplicationDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //ADMIN accessing applications to their properties
    public List<Application> getApplicationsByManagerUsername(String username) {
        List<Application> applications = new ArrayList<>();
//        String sql = sqlStringForManagers();
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, a.full_name, " +
                "a.email, a.is_above_18, a.has_roomates, a.roomate_names " +
                "FROM applications a " +
                "JOIN properties p ON a.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id " +
                "WHERE username = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while(results.next()){
                Application application = mapRowToApplication(results);
                applications.add(application);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applications;
    }

    @Override //USER accessing their own applications
    public List<Application> getApplicationsByUsername(String username) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, a.full_name, " +
                "a.email, a.is_above_18, a.has_roomates, a.roomate_names " +
                "FROM applications a " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE username = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while(results.next()){
                Application application = mapRowToApplication(results);
                applications.add(application);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applications;
    }

    @Override //ADMIN would only access
    public List<Application> getApplicationsByStatus(String username,String status) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, " +
                "a.full_name, a.email, a.is_above_18, a.has_roomates, a.roomate_names roomate_names " +
                "FROM applications a " +
                "JOIN properties p ON a.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ? AND a.status = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, status);
            while(results.next()){
                Application application = mapRowToApplication(results);
                applications.add(application);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applications;
    }

    @Override //ADMIN would only access
    public List<Application> getApplicationsByPropertyId(String username, int propertyId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, " +
                "a.full_name, a.email, a.is_above_18, a.has_roomates, a.roomate_names roomate_names " +
                "FROM applications a " +
                "JOIN properties p ON a.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id "+
                "WHERE u.username = ? AND p.property_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username,propertyId);
            while(results.next()){
                Application application = mapRowToApplication(results);
                applications.add(application);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applications;
    }

    @Override //ADMIN would only access
    public Application getApplicationByIdManager(String username,int applicationId) {
        Application application = null;
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, a.full_name, " +
                "a.email, a.is_above_18, a.has_roomates, a.roomate_names roomate_names " +
                "FROM applications a " +
                "JOIN properties p ON a.property_id = p.property_id " +
                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
                "JOIN users u ON mp.user_id = u.user_id " +
                "WHERE u.username = ? AND a.application_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username, applicationId);
            if(result.next()){
                application = mapRowToApplication(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return application;
    }

    @Override
    public Application getApplicationByIdUsername(String username, int applicationId) {
        Application application = null;
        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, a.full_name, " +
                "a.email, a.is_above_18, a.has_roomates, a.roomate_names " +
                "FROM applications a " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE username = ? AND application_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username, applicationId);
            if(result.next()){
                application = mapRowToApplication(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return application;
    }

    @Override
    public Application getApplicationById(int applicationId) {
        Application application = null;
        String sql = "SELECT application_id, user_id, property_id, status, has_roomates, full_name, email, " +
                " is_above_18, has_roomates, roomate_names " +
                "FROM applications " +
                "WHERE application_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, applicationId);
            if(result.next()){
                application = mapRowToApplication(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return application;
    }

    @Override
    public Application createApplication(Application application) {
        Application newApplication = null;
        String sql = "INSERT INTO applications (user_id, property_id, status, " +
                "full_name, email, is_above_18, has_roomates, roomate_names) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING application_id";
        try{
            int newApplicationId = jdbcTemplate.queryForObject(sql, int.class, application.getUserId(), application.getPropertyId(),
                    application.getStatus(), application.getFullName(), application.getEmail(), application.isAbove18(),
                    application.isHasRoomates(), application.getRoomateNames());
            newApplication = getApplicationById(newApplicationId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newApplication;
    }

    @Override //ADMIN would approve or reject
    public Application updateApplication(Application application) {
        Application updatedApplication = null;
        String sql = "UPDATE applications SET user_id = ?, property_id = ?, status = ?, full_name = ?, email = ?, " +
                "has_roomates = ?, is_Above_18 = ?, roomate_names = ? WHERE application_id = ?";
        try{
            int rowsAffected = jdbcTemplate.update(sql, application.getUserId(), application.getPropertyId(),
                    application.getStatus(), application.getFullName(), application.getEmail(), application.isAbove18(),
                    application.isHasRoomates(), application.getRoomateNames(),
                    application.getApplicationId());
            if(rowsAffected == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updatedApplication = getApplicationById(application.getApplicationId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedApplication;
    }

    @Override
    public int deleteApplication(int userId,int applicationId) {
        int rowsAffected;
        String sql = "DELETE FROM applications a WHERE a.user_id = ? AND application_id = ?";
        try{
            rowsAffected = jdbcTemplate.update(sql,userId, applicationId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }
//    private String sqlStringForManagers(){
//        String sql = "SELECT a.application_id, a.user_id, a.property_id, a.status, a.has_roomates, " +
//                "a.full_name, a.email, a.is_above_18, a.has_roomates, a.roomate_names roomate_names " +
//                "FROM applications a " +
//                "JOIN properties p ON a.property_id = p.property_id " +
//                "JOIN manager_profiles mp ON p.manager_id = mp.manager_id " +
//                "JOIN users u ON mp.user_id = u.user_id "+
//                "WHERE u.username = ?";
//        return sql;
//    }
    private Application mapRowToApplication(SqlRowSet results){
        Application application = new Application();
        application.setApplicationId(results.getInt("application_id"));
        application.setUserId(results.getInt("user_id"));
        application.setPropertyId(results.getInt("property_id"));
        application.setStatus(results.getString("status"));
        application.setFullName(results.getString("full_name"));
        application.setEmail(results.getString("email"));
        application.setAbove18(results.getBoolean("is_above_18"));
        application.setHasRoomates(results.getBoolean("has_roomates"));
        application.setRoomateNames(results.getString("roomate_names"));
        return application;
    }
}
