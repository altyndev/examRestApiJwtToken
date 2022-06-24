package com.peaksoft.examrestapijwttoken.mapper.view;

import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import com.peaksoft.examrestapijwttoken.model.Company;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyViewMapper {

    public CompanyResponse viewCompany(Company company) {

        if (company == null) {

            return null;
        }

        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());

        response.setName(company.getName());

        response.setAddress(company.getAddress());

        response.setCreated(company.getCreated());

        response.setEnabled(company.isEnabled());

        return response;
    }

    public List<CompanyResponse> view(List<Company> companies) {

        List<CompanyResponse> responses = new ArrayList<>();

        for (Company company : companies) {

            responses.add(viewCompany(company));
        }

        return responses;
    }
}
