package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.request.TeacherRequest;
import com.peaksoft.examrestapijwttoken.dto.response.TeacherResponse;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import com.peaksoft.examrestapijwttoken.mapper.edit.TeacherEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.TeacherViewMapper;
import com.peaksoft.examrestapijwttoken.model.Course;
import com.peaksoft.examrestapijwttoken.model.Teacher;
import com.peaksoft.examrestapijwttoken.repository.CourseRepository;
import com.peaksoft.examrestapijwttoken.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final CompanyService companyService;

    public TeacherResponse create(Long id, TeacherRequest request) {
        Course course = courseService.courseId(id);
        Teacher teacher = editMapper.create(request);
        if (userService.findAllEmail(request.getEmail())) {
            throw new ThereIsSuchAName("already exists with this email address");
        }
        if (course.getTeacher() != null) {
            throw new ThereIsSuchAName("This " + course.getName() + " course has a teacher "
                    + course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        } else {
            if (!findByName(request, id)) {
                userService.createInstructor(userRequestInTeacher(request));
                teacher.setCourse(course);
                repository.save(teacher);
            }
        }
        return viewMapper.viewTeacher(teacher);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        if (userService.findAllEmail(request.getEmail())) {
            throw new ThereIsSuchAName("already exists with this email address");
        }
        Teacher teacher = teacherId(id);
        userService.updateUser(teacher.getEmail(), request.getEmail(),
                request.getFirstName(), request.getPassword());
        editMapper.update(teacher, request);
        return viewMapper.viewTeacher(repository.save(teacher));
    }

    public TeacherResponse findById(Long id) {
        return viewMapper.viewTeacher(teacherId(id));
    }

    public TeacherResponse deleteById(Long id) {
        Teacher teacher = teacherId(id);
        repository.delete(teacher);
        return viewMapper.viewTeacher(teacher);
    }

    public List<TeacherResponse> findAll(Long id) {
        companyService.companyId(id);
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = courseRepository.findAllById(id);
        for (Course course : courses) {
            if (course.getTeacher() != null) {
                teachers.add(course.getTeacher());
            }
        }
        return viewMapper.view(teachers);
    }

    private RegisterRequest userRequestInTeacher(TeacherRequest teacherRequest) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(teacherRequest.getEmail());
        registerRequest.setFirstName(teacherRequest.getFirstName());
        registerRequest.setPassword(teacherRequest.getPassword());

        return registerRequest;
    }

    private Boolean findByName(TeacherRequest request, Long id) {
        for (Course course : courseRepository.findAllById(id)) {
            if (course.getTeacher() != null) {
                if (course.getTeacher().getFirstName().equals(request.getFirstName()) &&
                        course.getTeacher().getLastName().equals(request.getLastName())) {
                    throw new ThereIsSuchAName("you will not be able to use this name "
                            + request.getFirstName() + " " + request.getLastName() + " it is already in use");
                }
            }
        }
        return false;
    }

    private Teacher teacherId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Teacher with id = " + id + " not found"));
    }

//    public TeacherResponseView findAllTeacherPagination(Long id, int page, int size) {
//        TeacherResponseView responseView = new TeacherResponseView();
//        Pageable pageable = PageRequest.of(page-1, size);
//        Course course = courseRepository.findById(id).get();
//        responseView.setResponses(view(search(pageable, course.getCompany().getId())));
//        return responseView;
//    }
//
//    private List<TeacherResponse> view(List<Teacher> teachers) {
//        List<TeacherResponse> responses = new ArrayList<>();
//        for (Teacher teacher : teachers) {
//            responses.add(viewMapper.viewTeacher(teacher));
//        }
//        return responses;
//    }
//
//    private List<Teacher> search(Pageable pageable, Long id) {
//        List<Teacher> teachers = new ArrayList<>();
//        List<Course> courses = courseRepository.searchAndPagination(id, pageable);
//        for (Course course : courses) {
//            if (course.getTeacher() != null) {
//                teachers.add(course.getTeacher());
//            }
//        }
//        return teachers;
//    }

//    public List<TeacherResponse> findAll() {
//        return view(repository.findAll());
//    }
}
