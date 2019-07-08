package ru.ankoks.jwtdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ankoks.jwtdemo.model.UserEntity;
import ru.ankoks.jwtdemo.model.dto.AuthenticationRequestDto;
import ru.ankoks.jwtdemo.model.dto.UserDto;
import ru.ankoks.jwtdemo.security.jwt.JwtTokenProvider;
import ru.ankoks.jwtdemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("sign-in")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            UserEntity user = userService.findByUserName(username);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("User with username: %s not found", username));
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("sign-up")
    public ResponseEntity signIn(@RequestBody UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUsername());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        UserEntity user = userService.register(userEntity);

        System.out.println(user);

        return ResponseEntity.ok(user);
    }
}
