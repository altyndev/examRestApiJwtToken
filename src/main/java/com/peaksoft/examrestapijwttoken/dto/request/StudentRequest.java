package com.peaksoft.examrestapijwttoken.dto.request;

import com.peaksoft.examrestapijwttoken.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String email;

    private StudyFormat studyFormat;
}
