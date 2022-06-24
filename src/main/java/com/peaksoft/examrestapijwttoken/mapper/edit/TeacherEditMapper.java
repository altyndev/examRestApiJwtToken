package com.peaksoft.examrestapijwttoken.mapper.edit;

import com.peaksoft.examrestapijwttoken.dto.request.TeacherRequest;
import com.peaksoft.examrestapijwttoken.model.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TeacherEditMapper {

    public Teacher create(TeacherRequest request) {

        if (request == null) {

            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setFirstName(request.getFirstName());

        teacher.setLastName(request.getLastName());

        teacher.setEmail(request.getEmail());

        teacher.setCreated(LocalDate.now());

        teacher.setEnabled(true);

        return teacher;
    }

    public void update(Teacher teacher, TeacherRequest request) {

        teacher.setFirstName(request.getFirstName());

        teacher.setLastName(request.getLastName());

        teacher.setEmail(request.getEmail());
    }
}