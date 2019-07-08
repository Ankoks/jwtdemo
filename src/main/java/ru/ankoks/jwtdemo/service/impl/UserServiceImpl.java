package ru.ankoks.jwtdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ankoks.jwtdemo.model.RoleEntity;
import ru.ankoks.jwtdemo.model.Status;
import ru.ankoks.jwtdemo.model.UserEntity;
import ru.ankoks.jwtdemo.repository.RoleRepository;
import ru.ankoks.jwtdemo.repository.UserRepository;
import ru.ankoks.jwtdemo.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserEntity user) {
        RoleEntity roleUser = roleRepository.findByName("ROLE_USER");

        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        user.setCreated(new Date());
        user.setUpdated(new Date());

        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
