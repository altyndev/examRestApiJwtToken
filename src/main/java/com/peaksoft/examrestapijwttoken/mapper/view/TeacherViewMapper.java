package com.peaksoft.examrestapijwttoken.mapper.view;

import com.peaksoft.examrestapijwttoken.dto.response.TeacherResponse;
import com.peaksoft.examrestapijwttoken.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher) {

        if (teacher == null) {

            return null;
        }

        TeacherResponse response = new TeacherResponse();

        response.setId(teacher.getId());

        response.setFirstName(teacher.getFirstName());

        response.setLastName(teacher.getLastName());

        response.setEmail(teacher.getEmail());

        response.setCreated(teacher.getCreated());

        response.setEnabled(teacher.isEnabled());

        return response;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {

        List<TeacherResponse> responses = new ArrayList<>();

        for (Teacher teacher : teachers) {

            responses.add(viewTeacher(teacher));
        }
        return responses;
    }
}
