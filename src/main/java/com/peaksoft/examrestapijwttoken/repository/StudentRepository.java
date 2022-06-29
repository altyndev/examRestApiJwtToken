package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.group.id = :id")
    List<Student> searchAndPagination(@Param("id") Long id, Pageable pageable);

    @Query("select s from Student s where s.group.id = :id")
    List<Student> findAllByGroupId(@Param("id") Long id);
}