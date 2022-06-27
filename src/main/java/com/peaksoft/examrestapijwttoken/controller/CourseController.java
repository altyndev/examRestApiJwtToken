package com.peaksoft.examrestapijwttoken.controller;

import com.peaksoft.examrestapijwttoken.dto.request.CourseRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CourseResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.CourseResponseView;
import com.peaksoft.examrestapijwttoken.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    @PostMapping("/save/{id}")
    @Operation(summary = "create course", description = "we can create course")
    public CourseResponse create(@RequestBody CourseRequest request,
                                 @PathVariable Long id) {

        return service.create(id, request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update course", description = "we can update course by id")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {

        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find course", description = "we can find course by id")
    public CourseResponse findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete course", description = "we can delete course by id")
    public CourseResponse delete(@PathVariable Long id) {

        return service.deleteById(id);
    }

    @GetMapping("/findAll/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "find all student and pagination",
            description = "we can get all student and pagination by id")
    public CourseResponseView findAllCourses(@RequestParam int page,
                                             @RequestParam int size,
                                             @PathVariable Long id) {

        return service.findAllCoursePagination(id, page, size);
    }
}
