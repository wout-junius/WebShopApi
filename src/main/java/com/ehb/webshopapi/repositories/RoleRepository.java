package com.ehb.webshopapi.repositories;

import com.ehb.webshopapi.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(String username);
}
