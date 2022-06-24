package com.peaksoft.examrestapijwttoken.mapper.edit;

import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.model.Company;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanyEditMapper {

    public Company create(CompanyRequest request) {

        if (request == null) {

            return null;
        }

        Company company = new Company();

        company.setName(request.getName());

        company.setAddress(request.getAddress());

        company.setCreated(LocalDate.now());

        company.setEnabled(true);

        return company;
    }

    public void update(Company company, CompanyRequest request) {

        company.setName(request.getName());

        company.setAddress(request.getAddress());
    }
}
