package com.peaksoft.examrestapijwttoken.mapper.edit;

import com.peaksoft.examrestapijwttoken.dto.request.GroupRequest;
import com.peaksoft.examrestapijwttoken.model.Group;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GroupEditMapper {

    public Group create(GroupRequest request) {

        if (request == null) {

            return null;
        }

        Group group = new Group();

        group.setName(request.getName());

        group.setDateOfStart(request.getDateOfStart());

        group.setDateOfFinish(request.getDateOfFinish());

        group.setCreated(LocalDate.now());

        group.setEnabled(true);

        return group;
    }

    public void update(Group group, GroupRequest request) {

        group.setName(request.getName());

        group.setDateOfStart(request.getDateOfStart());

        group.setDateOfFinish(request.getDateOfFinish());
    }
}
