package com.techelevator.service;

import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int getManagerIdFromUserId(Principal principal) {
        try {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            return userDao.getManagerIdFromUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public int getTenantIdFromUserId(Principal principal) {
        try {
            int userId = userDao.getUserByUsername(principal.getName()).getId();
            return userDao.getTenantIdFromUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


}
