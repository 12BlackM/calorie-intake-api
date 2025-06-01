package com.miczarne.cia.user.service;

import com.miczarne.cia.user.model.Role;
import com.miczarne.cia.user.model.UserEntity;
import com.miczarne.cia.user.model.UserRoleEntity;
import com.miczarne.cia.user.repository.UserRepository;
import com.miczarne.cia.user.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(final UserRepository userRepository, final UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public boolean checkIfUserExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Optional<UserEntity> getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void addNewUser(String username, String password) {
        final UserRoleEntity defaultRole = userRoleRepository.findByName(Role.USER.getName());
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.getRoles().add(defaultRole);
        userRepository.save(userEntity);
    }
}
