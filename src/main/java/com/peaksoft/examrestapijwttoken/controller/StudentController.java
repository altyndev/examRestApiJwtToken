package com.peaksoft.examrestapijwttoken.controller;

import com.peaksoft.examrestapijwttoken.dto.request.StudentRequest;
import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.StudentResponseView;
import com.peaksoft.examrestapijwttoken.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    @PostMapping("/save/{id}")
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request,
                                  @PathVariable Long id) {

        return service.create(id, request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {

        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public StudentResponse findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public StudentResponse delete(@PathVariable Long id) {

        return service.deleteById(id);
    }

    @GetMapping("/findAll/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    @Operation(summary = "find all student and pagination", description = "we can get all student and pagination by id")
    public List<StudentResponse> findAll(@PathVariable Long id) {

        return service.findAll(id);
    }

//    @GetMapping("/findAll/{id}")
//    @PreAuthorize("hasAuthority('STUDENT')")
//    @Operation(summary = "find all student and pagination", description = "we can get all student and pagination by id")
//    public StudentResponseView findAllStudents(@RequestParam int page,
//                                              @RequestParam int size,
//                                              @PathVariable Long id) {
//
//        return service.findAllStudentPagination(id, page, size);
//    }

}
