package com.peaksoft.examrestapijwttoken.mapper.view;

import com.peaksoft.examrestapijwttoken.dto.response.CourseResponse;
import com.peaksoft.examrestapijwttoken.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course) {

        if (course == null) {

            return null;
        }

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());

        response.setName(course.getName());

        response.setDuration(course.getDuration());

        response.setCreated(course.getCreated());

        response.setEnabled(course.isEnabled());

        return response;
    }

    public List<CourseResponse> view(List<Course> courses) {

        List<CourseResponse> responses = new ArrayList<>();

        for (Course course : courses) {

            responses.add(viewCourse(course));
        }

        return responses;
    }
}
