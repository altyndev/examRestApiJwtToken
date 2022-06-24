package com.peaksoft.examrestapijwttoken.dto.responseView;

import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseView {

    List<StudentResponse> responses;
}
