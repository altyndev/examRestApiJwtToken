package com.peaksoft.examrestapijwttoken.controller;

import com.peaksoft.examrestapijwttoken.dto.request.StudentRequest;
import com.peaksoft.examrestapijwttoken.dto.request.TeacherRequest;
import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.dto.response.TeacherResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.StudentResponseView;
import com.peaksoft.examrestapijwttoken.dto.responseView.TeacherResponseView;
import com.peaksoft.examrestapijwttoken.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/save/{id}")
    @Operation(summary = "create teacher", description = "we can create teacher")
    public TeacherResponse create(@RequestBody TeacherRequest request,
                                  @PathVariable Long id) {

        return service.create(id, request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update teacher", description = "we can update teacher by id")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {

        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find teacher", description = "we can find teacher by id")
    public TeacherResponse findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete teacher", description = "we can delete teacher by id")
    public TeacherResponse delete(@PathVariable Long id) {

        return service.deleteById(id);
    }

    @GetMapping("/findAll/{id}")
    @Operation(summary = "find all teacher and pagination", description = "we can get all teacher and pagination by id")
    public TeacherResponseView findAllStudents(@RequestParam int page,
                                               @RequestParam int size,
                                               @PathVariable Long id) {

        return service.findAllTeacherPagination(id, page, size);
    }
}
