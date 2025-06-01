package com.miczarne.cia.user.repository;

import com.miczarne.cia.user.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    public UserRoleEntity findByName(String name);
}
