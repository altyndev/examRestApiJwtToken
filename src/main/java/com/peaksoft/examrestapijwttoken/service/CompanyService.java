package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import com.peaksoft.examrestapijwttoken.mapper.edit.CompanyEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.CompanyViewMapper;
import com.peaksoft.examrestapijwttoken.model.Company;
import com.peaksoft.examrestapijwttoken.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEditMapper companyEditMapper;
    private final CompanyViewMapper companyViewMapper;

    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = companyEditMapper.create(companyRequest);
        if (!findByName(companyRequest)){
            companyRepository.save(company);
        }
        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        Company company = companyId(id);
        if (!findByName(companyRequest)) {
            companyEditMapper.update(company, companyRequest);
            companyRepository.save(company);
        }
        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse findById(Long id) {
        return companyViewMapper.viewCompany(companyId(id));
    }

    public CompanyResponse deleteById(Long id) {
        Company company = companyId(id);
        companyRepository.delete(company);
        return companyViewMapper.viewCompany(company);
    }

    public List<CompanyResponse> findAll() {
        return companyViewMapper.view(companyRepository.findAll());
    }

    public Company companyId(Long id) {
        return companyRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Company with id = " + id + " not found"));
    }

    private Boolean findByName(CompanyRequest companyRequest) {
        for (Company company : companyRepository.findAll()) {
            if (company.getName().equals(companyRequest.getName())){
                throw  new ThereIsSuchAName("you will not be able to use this name "
                        + companyRequest.getName() + " it is already in use");
            }
        }
        return false;
    }

//    public CompanyResponseView findAllCompanyPagination(String text,
//                                                        int page, int size) {
//        CompanyResponseView responseView = new CompanyResponseView();
//        Pageable pageable = PageRequest.of(page-1, size);
//        responseView.setResponses(view(search(text,pageable)));
//        return responseView;
//    }

//    private List<CompanyResponse> view(List<Company> companies) {
//        List<CompanyResponse> responses = new ArrayList<>();
//        for (Company company : companies) {
//            responses.add(companyViewMapper.viewCompany(company));
//        }
//        return responses;
//    }
//
//    private List<Company> search(String name, Pageable pageable) {
//        String text = name == null ? "" : name;
//        return companyRepository.searchAndPagination(text.toUpperCase(), pageable);
//    }
}
