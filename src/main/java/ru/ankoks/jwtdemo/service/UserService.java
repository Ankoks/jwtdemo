package ru.ankoks.jwtdemo.service;

import ru.ankoks.jwtdemo.model.UserEntity;

import java.util.List;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
public interface UserService {

    UserEntity register(UserEntity user);

    List<UserEntity> getAll();

    UserEntity findByUserName(String name);

    UserEntity findById(Long id);

    void delete(Long id);
}
