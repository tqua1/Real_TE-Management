package com.financialtracker.vault.dao;

import com.financialtracker.vault.model.RegisterUserDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {


        List<User> getUsers();

        User getUserById(int id);

        User getUserByUsername(String username);

        User createUser(RegisterUserDto user);
}
