package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.CompanyRequest;
import com.peaksoft.examrestapijwttoken.dto.request.CourseRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CourseResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.CourseResponseView;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import com.peaksoft.examrestapijwttoken.mapper.edit.CourseEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.CourseViewMapper;
import com.peaksoft.examrestapijwttoken.model.Company;
import com.peaksoft.examrestapijwttoken.model.Course;
import com.peaksoft.examrestapijwttoken.repository.CompanyRepository;
import com.peaksoft.examrestapijwttoken.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;
    private final CompanyService service;

    public CourseResponse create(Long id, CourseRequest request) {
        Company company = service.companyId(id);
        Course course = editMapper.create(request);
        if (!findByName(request, id)) {
            course.setCompany(company);
            repository.save(course);
        }
        return viewMapper.viewCourse(course);
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Course course = courseId(id);
        if (!findByName(request, course.getCompany().getId())) {
            editMapper.update(course, request);
        }
        return viewMapper.viewCourse(repository.save(course));
    }

    public CourseResponse findById(Long id) {
        return viewMapper.viewCourse(courseId(id));
    }

    public CourseResponse deleteById(Long id) {
        Course course = courseId(id);
        repository.delete(course);
        return viewMapper.viewCourse(course);
    }

    public List<CourseResponse> findAll(Long id) {
        Company company = service.companyId(id);
        List<Course> courses = new ArrayList<>();
        if (company != null) {
            courses.addAll(repository.findAllById(id));
        }
        return viewMapper.view(courses);
    }

    private Boolean findByName(CourseRequest courseRequest, Long id) {
        for (Course course : repository.findAllById(id)) {
            if (course.getName().equals(courseRequest.getName())){
                throw  new ThereIsSuchAName("you will not be able to use this name "
                        + courseRequest.getName() + " it is already in use");
            }
        }
        return false;
    }

    public Course courseId(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Course with id = " + id + " not found"));
    }

//    public CourseResponseView findAllCoursePagination(Long id, int page, int size) {
//        CourseResponseView responseView = new CourseResponseView();
//        Pageable pageable = PageRequest.of(page-1, size);
//        responseView.setResponses(view(search(pageable, id)));
//        return responseView;
//    }
//
//    private List<CourseResponse> view(List<Course> courses) {
//        List<CourseResponse> responses = new ArrayList<>();
//        for (Course course : courses) {
//            responses.add(viewMapper.viewCourse(course));
//        }
//        return responses;
//    }
//
//    private List<Course> search(Pageable pageable, Long id) {
//        return repository.searchAndPagination(id, pageable);
//    }
}
