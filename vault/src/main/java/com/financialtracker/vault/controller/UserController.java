package com.financialtracker.vault.controller;

import com.financialtracker.vault.dao.UserDao;
import com.financialtracker.vault.exception.ServiceException;
import com.financialtracker.vault.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@RestController
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    public class UserController {
        private final UserService userService;
        private final UserDao userDao;

        public UserController(UserService userService, UserDao userDao) {
            this.userService = userService;
            this.userDao = userDao;
        }
        @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
        @GetMapping("/manager-id")
        public int getUserIdFromUserId(Principal principal) {
            return userService.getUserIdFromUserId(principal);
        }

}
