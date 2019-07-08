package ru.ankoks.jwtdemo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ankoks.jwtdemo.model.UserEntity;
import ru.ankoks.jwtdemo.security.jwt.JwtUser;
import ru.ankoks.jwtdemo.security.jwt.JwtUserFactory;
import ru.ankoks.jwtdemo.service.UserService;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByUserName(userName);

        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("User with username: {%s} not found", userName));
        }

        JwtUser jwtUser = JwtUserFactory.create(userEntity);

        log.info("IN loadUserByUsername - user with username: {} successfully loaded", jwtUser.getUsername());
        return jwtUser;
    }
}
