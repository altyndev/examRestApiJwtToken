package com.peaksoft.examrestapijwttoken.controller;

import com.peaksoft.examrestapijwttoken.dto.request.GroupRequest;
import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import com.peaksoft.examrestapijwttoken.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService service;

    @PostMapping("/save")
    @Operation(summary = "create group", description = "we can create group")
    public GroupResponse create(@RequestBody GroupRequest request) {

        return service.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update group", description = "we can update group by id")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {

        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find group", description = "we can find group by id")
    public GroupResponse findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete group", description = "we can delete group by id")
    public GroupResponse delete(@PathVariable Long id) {

        return service.deleteById(id);
    }

    @GetMapping("/findAll")
    @Operation(summary = "find all groups", description = "we can get all group")
    public List<GroupResponse> findAll() {

        return service.findAll();
    }
}
