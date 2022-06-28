package com.peaksoft.examrestapijwttoken;

import com.peaksoft.examrestapijwttoken.model.Role;
import com.peaksoft.examrestapijwttoken.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamRestApiJwtTokenApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExamRestApiJwtTokenApplication.class, args);
    }
}
