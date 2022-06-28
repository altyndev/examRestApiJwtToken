package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.dto.response.GroupResponse;
import com.peaksoft.examrestapijwttoken.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("delete from Group where id =: id")
    GroupResponse findByIdDelete(Long id);
}