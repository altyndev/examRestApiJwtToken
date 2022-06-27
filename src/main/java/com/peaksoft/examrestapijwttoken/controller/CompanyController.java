package com.peaksoft.examrestapijwttoken.controller;


import com.peaksoft.examrestapijwttoken.dto.responseView.CompanyResponseView;
import io.swagger.v3.oas.annotations.Operation;
import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import com.peaksoft.examrestapijwttoken.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/save")
    @Operation(summary = "create company", description = "we can create company")
    public CompanyResponse create(@RequestBody CompanyRequest companyRequest) {

        return companyService.create(companyRequest);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update company", description = "we can update company by id")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {

        return companyService.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find company", description = "we can find company by id")
    public CompanyResponse findById(@PathVariable Long id) {

        return companyService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete company", description = "we can delete company by id")
    public CompanyResponse delete(@PathVariable Long id) {

        return companyService.deleteByid(id);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    @Operation(summary = "Get all Company and search", description = "we can get all companies and search")
    public List<CompanyResponse> findAll() {

        return companyService.findAll();
    }
//    @GetMapping
//    @PreAuthorize("hasAuthority('STUDENT')")
//    @Operation(summary = "Get all Company and search", description = "we can get all companies and search")
//    public CompanyResponseView findAllCompanies(
//            @RequestParam(name = "text", required = false) String text,
//            @RequestParam(name = "page", required = false) int page,
//            @RequestParam(name = "size", required = false) int size) {
//
//        return companyService.findAllCompanyPagination(text, page, size);
//    }
}
