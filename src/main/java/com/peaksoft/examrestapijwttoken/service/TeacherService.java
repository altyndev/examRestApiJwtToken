package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.request.TeacherRequest;
import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.dto.response.TeacherResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.TeacherResponseView;
import com.peaksoft.examrestapijwttoken.mapper.edit.TeacherEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.TeacherViewMapper;
import com.peaksoft.examrestapijwttoken.model.Course;
import com.peaksoft.examrestapijwttoken.model.Role;
import com.peaksoft.examrestapijwttoken.model.Student;
import com.peaksoft.examrestapijwttoken.model.Teacher;
import com.peaksoft.examrestapijwttoken.repository.CourseRepository;
import com.peaksoft.examrestapijwttoken.repository.RoleRepository;
import com.peaksoft.examrestapijwttoken.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;
    private final CourseRepository courseRepository;
    private final UserService userService;

    public TeacherResponse create(Long id, TeacherRequest request) {

        Course course = courseRepository.findById(id).get();

        Teacher teacher = editMapper.create(request);

        if (course.getTeacher() != null) {

            update(course.getTeacher().getId(), request);

        }else {

            userService.createInstructor(userReguestInTeacher(request));
            teacher.setCourse(course);
            repository.save(teacher);
        }
        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {

        Teacher teacher = repository.findById(id).get();

        editMapper.update(teacher, request);

        return viewMapper.viewTeacher(repository.save(teacher));
    }

    public TeacherResponse findById(Long id) {

        Teacher teacher = repository.findById(id).get();

        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponse deleteById(Long id) {

        Teacher teacher = repository.findById(id).get();

        repository.delete(teacher);

        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponseView findAllTeacherPagination(Long id, int page, int size) {

        TeacherResponseView responseView = new TeacherResponseView();

        Pageable pageable = PageRequest.of(page-1, size);

        Course course = courseRepository.findById(id).get();

        responseView.setResponses(view(search(pageable, course.getCompany().getId())));

        return responseView;
    }

    private List<TeacherResponse> view(List<Teacher> teachers) {

        List<TeacherResponse> responses = new ArrayList<>();

        for (Teacher teacher : teachers) {

            responses.add(viewMapper.viewTeacher(teacher));
        }
        return responses;
    }

    private List<Teacher> search(Pageable pageable, Long id) {

        List<Teacher> teachers = new ArrayList<>();

        List<Course> courses = courseRepository.searchAndPagination(id, pageable);

        for (Course course : courses) {

            if (course.getTeacher() != null) {

                teachers.add(course.getTeacher());
            }
        }

        return teachers;
    }

    public List<TeacherResponse> findAll() {
        return view(repository.findAll());
    }

    private RegisterRequest userReguestInTeacher(TeacherRequest teacherRequest) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(teacherRequest.getEmail());
        registerRequest.setFirstName(teacherRequest.getFirstName());
        registerRequest.setPassword(teacherRequest.getPassword());

        return registerRequest;
    }
}
