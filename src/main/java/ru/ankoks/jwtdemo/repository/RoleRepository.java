package ru.ankoks.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ankoks.jwtdemo.model.RoleEntity;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
