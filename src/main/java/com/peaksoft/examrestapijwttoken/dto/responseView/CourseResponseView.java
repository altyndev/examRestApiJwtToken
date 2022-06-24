package com.peaksoft.examrestapijwttoken.dto.responseView;
import com.peaksoft.examrestapijwttoken.dto.response.CourseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseView {

    List<CourseResponse> responses;
}
