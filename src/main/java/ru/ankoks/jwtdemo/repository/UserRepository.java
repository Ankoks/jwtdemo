package ru.ankoks.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ankoks.jwtdemo.model.UserEntity;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String name);
}
