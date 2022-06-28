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
        if (!request.getName().equals(course.getName()) &&
                !request.getName().equals("string") && request.getName() != null) {
            course.setName(request.getName());
        }
        if (!request.getDuration().equals(course.getDuration()) &&
        !request.getDuration().equals("string") && request.getDuration() != null) {
            course.setDuration(request.getDuration());
        }
    }
}
