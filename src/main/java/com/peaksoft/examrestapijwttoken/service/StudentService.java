package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.request.StudentRequest;
import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import com.peaksoft.examrestapijwttoken.mapper.edit.StudentEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.StudentViewMapper;
import com.peaksoft.examrestapijwttoken.model.Group;
import com.peaksoft.examrestapijwttoken.model.Student;
import com.peaksoft.examrestapijwttoken.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;
    private final UserService userService;
    private final GroupService service;

    public StudentResponse create(Long id, StudentRequest request) {
        if (userService.findAllEmail(request.getEmail())) {
            throw new ThereIsSuchAName("already exists with this email address");
        }
        Group group = service.groupId(id);
        Student student = editMapper.create(request);
        if (!findByName(request, id)) {
            userService.createStudent(userRequestInStudent(request));
            student.setGroup(group);
            repository.save(student);
        }
        return viewMapper.viewStudent(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        if (userService.findAllEmail(request.getEmail())) {
            throw new ThereIsSuchAName("already exists with this email address");
        }
        Student student = studentId(id);
        userService.updateUser(student.getEmail(), request.getEmail(),
                request.getFirstName(), request.getPassword());

        if (!findByName(request, student.getGroup().getId())) {
            editMapper.update(student, request);
        }
        return viewMapper.viewStudent(repository.save(student));
    }

    public StudentResponse findById(Long id) {
        return viewMapper.viewStudent(studentId(id));
    }

    public StudentResponse deleteById(Long id) {
        Student student = studentId(id);
        repository.delete(student);
        return viewMapper.viewStudent(student);
    }

    public List<StudentResponse> findAll(Long id) {
        Group group = service.groupId(id);
        List<Student> students = new ArrayList<>();
        if (group != null) {
            students.addAll(repository.findAllByGroupId(id));
        }
        return viewMapper.view(students);
    }

    private Student studentId(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Student with id = " + id + " not found"));
    }

    private Boolean findByName(StudentRequest request, Long id) {
        List<Student> students = repository.findAllByGroupId(id);
        for (Student student : students) {
            if (student.getFirstName().equals(request.getFirstName()) &&
            student.getLastName().equals(request.getLastName())) {
                throw new ThereIsSuchAName("you will not be able to use this name "
                        + request.getFirstName() + " "+ request.getLastName() + " it is already in use");
            }
        }
        return false;
    }
    private RegisterRequest userRequestInStudent(StudentRequest studentRequest) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(studentRequest.getEmail());
        registerRequest.setFirstName(studentRequest.getFirstName());
        registerRequest.setPassword(studentRequest.getPassword());
        return registerRequest;
    }

//    public StudentResponseView findAllStudentPagination(Long id, int page, int size) {
//        StudentResponseView responseView = new StudentResponseView();
//        Pageable pageable = PageRequest.of(page-1, size);
//        responseView.setResponses(view(search(pageable, id)));
//        return responseView;
//    }
//
//    private List<StudentResponse> view(List<Student> students) {
//        List<StudentResponse> responses = new ArrayList<>();
//        for (Student student : students) {
//            responses.add(viewMapper.viewStudent(student));
//        }
//        return responses;
//    }
//
//    private List<Student> search(Pageable pageable, Long id) {
//        return repository.searchAndPagination(id, pageable);
//    }

//    public List<StudentResponse> findAll() {
//        return view(repository.findAll());
//    }
}
