package com.peaksoft.examrestapijwttoken.mapper.edit;

import com.peaksoft.examrestapijwttoken.dto.request.StudentRequest;
import com.peaksoft.examrestapijwttoken.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StudentEditMapper {

    public Student create(StudentRequest request) {

        if (request == null) {

            return null;
        }

        Student student = new Student();

        student.setFirstName(request.getFirstName());

        student.setLastName(request.getLastName());

        student.setEmail(request.getEmail());

        student.setStudyFormat(request.getStudyFormat());

        student.setCreated(LocalDate.now());

        student.setEnabled(true);

        return student;
    }

    public void update(Student student, StudentRequest request) {

        student.setFirstName(request.getFirstName());

        student.setLastName(request.getLastName());

        student.setEmail(request.getEmail());

        student.setStudyFormat(request.getStudyFormat());
    }
}
