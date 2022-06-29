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
        if (!request.getFirstName().equals(student.getFirstName()) &&
                request.getFirstName() != null) {
            student.setFirstName(request.getFirstName());
        }
        if (!request.getLastName().equals(student.getLastName()) &&
                request.getLastName() != null) {
            student.setLastName(request.getLastName());
        }
        if (!request.getEmail().equals(student.getEmail()) &&
                request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }
        student.setStudyFormat(request.getStudyFormat());
    }
}
