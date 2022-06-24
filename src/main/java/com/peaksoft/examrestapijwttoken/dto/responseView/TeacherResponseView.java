package com.peaksoft.examrestapijwttoken.dto.responseView;

import com.peaksoft.examrestapijwttoken.dto.response.TeacherResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherResponseView {

    List<TeacherResponse> responses;
}
