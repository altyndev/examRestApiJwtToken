package com.peaksoft.examrestapijwttoken.controller;


import com.peaksoft.examrestapijwttoken.dto.responseView.CompanyResponseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import com.peaksoft.examrestapijwttoken.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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

    @GetMapping
    @Operation(summary = "Get all Company and search", description = "we can get all companies and search")
    public CompanyResponseView findAllCompanies(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam int page, @RequestParam int size) {

        return companyService.findAllCompanyPagination(text, page, size);
    }
}
