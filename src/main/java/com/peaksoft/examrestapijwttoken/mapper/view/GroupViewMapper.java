package com.peaksoft.examrestapijwttoken.mapper.view;

import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import com.peaksoft.examrestapijwttoken.model.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupViewMapper {

    public GroupResponse viewGroup(Group group) {

        if (group == null) {

            return null;
        }

        GroupResponse response = new GroupResponse();

        response.setId(group.getId());

        response.setName(group.getName());

        response.setDateOfStart(group.getDateOfStart());

        response.setDateOfFinish(group.getDateOfFinish());

        response.setCreated(group.getCreated());

        response.setEnabled(group.isEnabled());

        return response;
    }

    public List<GroupResponse> view(List<Group> groups) {

        List<GroupResponse> responses = new ArrayList<>();

        for (Group group : groups) {

            responses.add(viewGroup(group));
        }

        return responses;
    }
}
