package com.peaksoft.examrestapijwttoken.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupRequest {

    private String name;

    private String dateOfStart;

    private String dateOfFinish;

    private List<Long> courseId;

//    @Override
//    public String toString() {
//        return "GroupRequest{" +
//                "name='" + name + '\'' +
//                ", dateOfStart='" + dateOfStart + '\'' +
//                ", dateOfFinish='" + dateOfFinish + '\'' +
//                '}';
//    }
}
