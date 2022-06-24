package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.model.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//    @Query("select t from Company c join Course co on c.id = co.company.id join Teacher t on co.id = t.course.id where c.id = :id")
//    List<Teacher> searchAndPagination(@Param("id") Long id, Pageable pageable);
}