package com.peaksoft.examrestapijwttoken.mapper.edit;

import com.peaksoft.examrestapijwttoken.dto.request.CourseRequest;
import com.peaksoft.examrestapijwttoken.model.Course;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CourseEditMapper {

    public Course create(CourseRequest request) {

        if (request == null) {

            return null;
        }

        Course course = new Course();

        course.setName(request.getName());

        course.setDuration(request.getDuration());

        course.setCreated(LocalDate.now());

        course.setEnabled(true);

        return course;
    }

    public void update(Course course, CourseRequest request) {

        course.setName(request.getName());

        course.setDuration(request.getDuration());
    }
}
