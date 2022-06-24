package com.peaksoft.examrestapijwttoken.service;

import com.peaksoft.examrestapijwttoken.dto.request.GroupRequest;
import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import com.peaksoft.examrestapijwttoken.mapper.edit.GroupEditMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.CourseViewMapper;
import com.peaksoft.examrestapijwttoken.mapper.view.GroupViewMapper;
import com.peaksoft.examrestapijwttoken.model.Group;
import com.peaksoft.examrestapijwttoken.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    private final GroupEditMapper editMapper;

    private final GroupViewMapper viewMapper;

    public GroupResponse create(GroupRequest request) {

        Group group = editMapper.create(request);

        repository.save(group);

        return viewMapper.viewGroup(group);
    }

    public GroupResponse update(Long id, GroupRequest request) {

        Group group = repository.findById(id).get();

        editMapper.update(group, request);

        return viewMapper.viewGroup(repository.save(group));
    }

    public GroupResponse findById(Long id) {

        Group group = repository.findById(id).get();

        return viewMapper.viewGroup(group);
    }

    public GroupResponse deleteById(Long id) {

        Group group = repository.findById(id).get();

        repository.delete(group);

        return viewMapper.viewGroup(group);
    }

    public List<GroupResponse> findAll() {

        return viewMapper.view(repository.findAll());
    }
}
