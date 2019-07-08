package ru.ankoks.jwtdemo.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.ankoks.jwtdemo.model.RoleEntity;
import ru.ankoks.jwtdemo.model.Status;
import ru.ankoks.jwtdemo.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthoritie(user.getRoles()),
                Status.ACTIVE.equals(user.getStatus()),
                user.getUpdated());
    }

    private static List<GrantedAuthority> mapToGrantedAuthoritie(List<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());
    }
}
