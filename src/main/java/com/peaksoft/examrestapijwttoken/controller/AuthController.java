package com.peaksoft.examrestapijwttoken.controller;

import com.peaksoft.examrestapijwttoken.dto.request.LoginMapper;
import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.response.LoginResponse;
import com.peaksoft.examrestapijwttoken.dto.response.RegisterResponse;
import com.peaksoft.examrestapijwttoken.exception.ValidationExceptionType;
import com.peaksoft.examrestapijwttoken.model.User;
import com.peaksoft.examrestapijwttoken.repository.UserRepository;
import com.peaksoft.examrestapijwttoken.security.jwt.JwtTokenUtil;
import com.peaksoft.examrestapijwttoken.service.RoleService;
import com.peaksoft.examrestapijwttoken.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {

    private final UserService service;

    private final UserRepository repository;

    private final JwtTokenUtil jwtTokenUtil;

    private final LoginMapper loginMapper;

    private final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {

        try {

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            User user = repository.findByEmail(token.getName()).get();

            return ResponseEntity.ok()
                    .body(loginMapper.loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));
        }catch (BadCredentialsException ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(loginMapper.loginView("", ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        roleService.create();
        return service.createAdmin(request);
    }
}
