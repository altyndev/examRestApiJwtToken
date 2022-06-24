package com.peaksoft.examrestapijwttoken.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyResponse {

    private Long id;

    private String name;

    private String address;

    private LocalDate created;

    private boolean enabled;
}
