package com.peaksoft.examrestapijwttoken.repository;

import com.peaksoft.examrestapijwttoken.model.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where upper(c.name) like concat('%', :text,'%') " +
            "or upper(c.address) like concat('%', :text,'%') ")
    List<Company> searchAndPagination(@Param("text") String text, Pageable pageable);
}