package com.byusluer.movierest.repository;

import com.byusluer.movierest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByRollName(String name);
}