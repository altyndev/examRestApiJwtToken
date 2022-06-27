package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);
}