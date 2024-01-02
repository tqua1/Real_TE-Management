package com.techelevator.service;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    int getManagerIdFromUserId(Principal principal);

    int getTenantIdFromUserId(Principal principal);

}
