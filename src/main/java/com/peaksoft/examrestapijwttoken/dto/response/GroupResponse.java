package com.peaksoft.examrestapijwttoken.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {

    private Long id;

    private String name;

    private String dateOfStart;

    private String dateOfFinish;

    private LocalDate created;

    private boolean enabled;
}
