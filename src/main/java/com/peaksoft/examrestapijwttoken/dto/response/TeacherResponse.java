package com.peaksoft.examrestapijwttoken.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherResponse {

    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private LocalDate created;

    private boolean enabled;
}
