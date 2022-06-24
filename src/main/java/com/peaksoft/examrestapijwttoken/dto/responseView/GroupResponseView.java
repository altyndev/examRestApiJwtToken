package com.peaksoft.examrestapijwttoken.dto.responseView;
import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseView {

    List<GroupResponse> responses;
}
