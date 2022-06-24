package com.peaksoft.examrestapijwttoken.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "company_sequence")
    private Long id;

    private String name;

    private String address;

    @OneToMany(cascade = {REMOVE, MERGE},  mappedBy = "company")
    private List<Course> courses;

    @CreatedDate
    private LocalDate created;

    private boolean enabled;
}
