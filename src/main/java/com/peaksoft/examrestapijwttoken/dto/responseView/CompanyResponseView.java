package com.peaksoft.examrestapijwttoken.dto.responseView;

import com.peaksoft.examrestapijwttoken.dto.response.CompanyResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponseView {

    List<CompanyResponse> responses;
}
