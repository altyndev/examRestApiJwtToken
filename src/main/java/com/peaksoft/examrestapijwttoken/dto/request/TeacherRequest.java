package com.peaksoft.examrestapijwttoken.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeacherRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
