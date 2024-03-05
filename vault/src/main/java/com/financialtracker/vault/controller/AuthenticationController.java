package com.financialtracker.vault.controller;

import com.financialtracker.vault.dao.UserDao;
import com.financialtracker.vault.exception.DaoException;
import com.financialtracker.vault.model.LoginDto;
import com.financialtracker.vault.model.LoginResponseDto;
import com.financialtracker.vault.model.RegisterUserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;





@RestController
    @CrossOrigin
    public class AuthenticationController {

        private final TokenProvider tokenProvider;
        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        private UserDao userDao;

        public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
            this.tokenProvider = tokenProvider;
            this.authenticationManagerBuilder = authenticationManagerBuilder;
            this.userDao = userDao;
        }

        @RequestMapping(path = "/login", method = RequestMethod.POST)
        public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            User user;
            user = userDao.getUserByUsername(loginDto.getUsername());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
        }

        @ResponseStatus(HttpStatus.CREATED)
        @RequestMapping(path = "/register", method = RequestMethod.POST)
        public void register(@Valid @RequestBody RegisterUserDto newUser) {
            User user = userDao.createUser(newUser);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
            }
        }
}
