package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.model.Role;
import com.peaksoft.examrestapijwttoken.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public void create() {
        Role admin = new Role();
        admin.setRoleName("ADMIN");

        Role instructor = new Role();
        instructor.setRoleName("INSTRUCTOR");

        Role student = new Role();
        student.setRoleName("STUDENT");

        roleRepository.save(admin);
        roleRepository.save(instructor);
        roleRepository.save(student);
    }
}
