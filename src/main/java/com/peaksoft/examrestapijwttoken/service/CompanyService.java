package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.CompanyResponseView;
import com.peaksoft.examrestapijwttoken.mapper.edit.CompanyEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.CompanyViewMapper;
import com.peaksoft.examrestapijwttoken.model.Company;
import com.peaksoft.examrestapijwttoken.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyEditMapper companyEditMapper;

    private final CompanyViewMapper companyViewMapper;

    public CompanyResponse create(CompanyRequest companyRequest) {

        Company company = companyEditMapper.create(companyRequest);

        companyRepository.save(company);

        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse update(Long id, CompanyRequest companyRequest) {

        Company company = companyRepository.findById(id).get();

        companyEditMapper.update(company, companyRequest);

        return companyViewMapper.viewCompany(companyRepository.save(company));
    }

    public CompanyResponse findById(Long id) {

        Company company = companyRepository.findById(id).get();

        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse deleteByid(Long id) {

        Company company = companyRepository.findById(id).get();

        companyRepository.delete(company);

        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponseView findAllCompanyPagination(String text, int page, int size) {

        CompanyResponseView responseView = new CompanyResponseView();

        Pageable pageable = PageRequest.of(page-1, size);

        responseView.setResponses(view(search(text,pageable)));

        return responseView;
    }

    private List<CompanyResponse> view(List<Company> companies) {

        List<CompanyResponse> responses = new ArrayList<>();

        for (Company company : companies) {

            responses.add(companyViewMapper.viewCompany(company));
        }
        return responses;
    }

    private List<Company> search(String name, Pageable pageable) {

        String text = name == null ? "" : name;

        return companyRepository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
