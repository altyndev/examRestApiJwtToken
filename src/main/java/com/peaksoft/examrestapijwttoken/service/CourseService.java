package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.CourseRequest;
import com.peaksoft.examrestapijwttoken.dto.response.CourseResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.CourseResponseView;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    private final CourseEditMapper editMapper;

    private final CourseViewMapper viewMapper;

    private final CompanyRepository companyRepository;

    public CourseResponse create(Long id, CourseRequest request) {

        Company company = companyRepository.findById(id).get();

        Course course = editMapper.create(request);

        course.setCompany(company);

        repository.save(course);

        return viewMapper.viewCourse(course);
    }

    public CourseResponse update(Long id, CourseRequest request) {

        Course course = repository.findById(id).get();

        editMapper.update(course, request);

        return viewMapper.viewCourse(repository.save(course));
    }

    public CourseResponse findById(Long id) {

        Course course = repository.findById(id).get();

        return viewMapper.viewCourse(course);
    }

    public CourseResponse deleteById(Long id) {

        Course course = repository.findById(id).get();

        repository.delete(course);

        return viewMapper.viewCourse(course);
    }

    public CourseResponseView findAllCoursePagination(Long id, int page, int size) {

        CourseResponseView responseView = new CourseResponseView();

        Pageable pageable = PageRequest.of(page-1, size);

        responseView.setResponses(view(search(pageable, id)));

        return responseView;
    }

    private List<CourseResponse> view(List<Course> courses) {

        List<CourseResponse> responses = new ArrayList<>();

        for (Course course : courses) {

            responses.add(viewMapper.viewCourse(course));
        }
        return responses;
    }

    private List<Course> search(Pageable pageable, Long id) {

        return repository.searchAndPagination(id, pageable);
    }
}
