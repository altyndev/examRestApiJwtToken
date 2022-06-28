package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.RegisterRequest;
import com.peaksoft.examrestapijwttoken.dto.request.StudentRequest;
import com.peaksoft.examrestapijwttoken.dto.response.RegisterResponse;
import com.peaksoft.examrestapijwttoken.dto.response.StudentResponse;
import com.peaksoft.examrestapijwttoken.dto.responseView.StudentResponseView;
import com.peaksoft.examrestapijwttoken.mapper.edit.StudentEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.StudentViewMapper;
import com.peaksoft.examrestapijwttoken.model.Group;
import com.peaksoft.examrestapijwttoken.model.Student;
import com.peaksoft.examrestapijwttoken.repository.GroupRepository;
import com.peaksoft.examrestapijwttoken.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;
    private final GroupRepository groupRepository;
    private final UserService userService;

    public StudentResponse create(Long id, StudentRequest request) {

        Group group = groupRepository.findById(id).get();

        Student student = editMapper.create(request);

        userService.createStudent(userRequestInStudent(request));
        student.setGroup(group);

        repository.save(student);

        return viewMapper.viewStudent(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {

        Student student = repository.findById(id).get();

        editMapper.update(student, request);

        return viewMapper.viewStudent(repository.save(student));
    }

    public StudentResponse findById(Long id) {

        Student student = repository.findById(id).get();

        return viewMapper.viewStudent(student);
    }

    public StudentResponse deleteById(Long id) {

        Student student = repository.findById(id).get();

        repository.delete(student);

        return viewMapper.viewStudent(student);
    }

    public StudentResponseView findAllStudentPagination(Long id, int page, int size) {

        StudentResponseView responseView = new StudentResponseView();

        Pageable pageable = PageRequest.of(page-1, size);

        responseView.setResponses(view(search(pageable, id)));

        return responseView;
    }

    private List<StudentResponse> view(List<Student> students) {

        List<StudentResponse> responses = new ArrayList<>();

        for (Student student : students) {

            responses.add(viewMapper.viewStudent(student));
        }
        return responses;
    }

    private List<Student> search(Pageable pageable, Long id) {

        return repository.searchAndPagination(id, pageable);
    }

    public List<StudentResponse> findAll() {
        return view(repository.findAll());
    }

    private RegisterRequest userRequestInStudent(StudentRequest studentRequest) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(studentRequest.getEmail());
        registerRequest.setFirstName(studentRequest.getFirstName());
        registerRequest.setPassword(studentRequest.getPassword());
        return registerRequest;
    }
}
