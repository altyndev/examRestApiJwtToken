package com.peaksoft.examrestapijwttoken.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {

    private Long id;

    private String name;

    private String duration;

    private LocalDate created;

    private boolean enabled;
}
