package com.peaksoft.examrestapijwttoken.mapper.view;

import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentViewMapper {

    public StudentResponse viewStudent(Student student) {

        if (student == null) {

            return null;
        }

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());

        response.setFirstName(student.getFirstName());

        response.setLastName(student.getLastName());

        response.setEmail(student.getEmail());

        response.setStudyFormat(student.getStudyFormat());

        response.setEnabled(student.isEnabled());

        response.setCreated(student.getCreated());

        return response;
    }

    public List<StudentResponse> view(List<Student> students) {

        List<StudentResponse> responses = new ArrayList<>();

        for (Student student : students) {

            responses.add(viewStudent(student));
        }

        return responses;
    }
}
