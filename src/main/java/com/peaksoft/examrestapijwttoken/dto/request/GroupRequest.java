package com.peaksoft.examrestapijwttoken.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {

    private String name;

    private String dateOfStart;

    private String dateOfFinish;
}
