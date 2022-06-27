package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.request.RoleRequest;
import com.peaksoft.examrestapijwttoken.dto.response.RegisterResponse;
import com.peaksoft.examrestapijwttoken.dto.response.RoleResponse;
import com.peaksoft.examrestapijwttoken.model.Role;
import com.peaksoft.examrestapijwttoken.model.User;
import com.peaksoft.examrestapijwttoken.repository.RoleRepository;
import com.peaksoft.examrestapijwttoken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public RegisterResponse create(RegisterRequest request) {

        User user = mapToEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = new Role();

        role.setRoleName("ADMIN");

        roleRepository.save(role);

        user.addRoles(role);

        userRepository.save(user);

        return mapToResponse(user);
    }

    private User mapToEntity(RegisterRequest request) {

        User user = new User();

        user.setEmail(request.getEmail());

        user.setFirstname(request.getFirstName());

        user.setPassword(request.getPassword());

        return user;
    }

    private RegisterResponse mapToResponse(User user) {

        if (user == null) {
            return null;
        }
        RegisterResponse response = new RegisterResponse();

        if (user.getId() != null) {

            response.setId(String.valueOf(user.getId()));
        }

        response.setEmail(user.getEmail());

        response.setFirstName(user.getFirstname());

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "User with email not found"));
    }

//    public RoleResponse createRole(RegisterRequest request) {
//        repository.save(requestInObject(request.getRole()));
//        Role role = requestInObject(request.getRole());
//        return objectInRequest(role);
//    }
//
//    private RoleResponse objectInRequest(Role role) {
//        RoleResponse response = new RoleResponse();
//        response.setRoleName(role.getRoleName());
//        response.setId(role.getId());
//        return response;
//    }
//
//    private Role requestInObject(String request) {
//        Role role = new Role();
//        role.setRoleName(request.toUpperCase());
//        return role;
//    }
}
