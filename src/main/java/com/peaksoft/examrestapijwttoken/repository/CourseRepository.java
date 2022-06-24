package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select co from Course co where co.company.id = :id")
    List<Course> searchAndPagination(@Param("id") Long id, Pageable pageable);

}