package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.GroupRequest;
import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import com.peaksoft.examrestapijwttoken.exception.ThereIsSuchAName;
import com.peaksoft.examrestapijwttoken.mapper.edit.GroupEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.GroupViewMapper;
import com.peaksoft.examrestapijwttoken.model.Course;
import com.peaksoft.examrestapijwttoken.model.Group;
import com.peaksoft.examrestapijwttoken.repository.CourseRepository;
import com.peaksoft.examrestapijwttoken.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;
    private final CourseService service;
    private final CourseRepository courseRepository;

    public GroupResponse create(GroupRequest request) {
        Group group = editMapper.create(request);
        if (!findByName(request)) {
            repository.save(addCourse(group, request.getCourseId()));
        }
        return viewMapper.viewGroup(group);
    }

    public GroupResponse update(Long id, GroupRequest request) {
        Group group = groupId(id);
        if (!findByName(request)) {
            editMapper.update(group, request);
        }
        return viewMapper.viewGroup(repository.save(group));
    }

    public GroupResponse findById(Long id) {
        return viewMapper.viewGroup(groupId(id));
    }

    public GroupResponse deleteById(Long id) {
        Group group = groupId(id);
        group.getCourses().forEach(x->x.getGroups().remove(group));
        courseRepository.saveAll(group.getCourses());
        repository.delete(group);
        return viewMapper.viewGroup(group);
    }

    public List<GroupResponse> findAll() {
        return viewMapper.view(repository.findAll());
    }

    public Group groupId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Group with id = " + id + " not found"));
    }

    private Group addCourse(Group group, List<Long> courseId) {
        for (Long aLong : courseId) {
            Course course = service.courseId(aLong);
            group.setCourses(course);
        }
        return group;
    }

    private Boolean findByName(GroupRequest request) {
        for (Group group : repository.findAll()) {
            if (group.getName().equals(request.getName())) {
                throw new ThereIsSuchAName("you will not be able to use this name "
                        + request.getName() + " it is already in use");
            }
        }
        return false;
    }
}
