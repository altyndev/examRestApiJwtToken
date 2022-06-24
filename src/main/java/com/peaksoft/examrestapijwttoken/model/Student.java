package com.peaksoft.examrestapijwttoken.model;

import com.peaksoft.examrestapijwttoken.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE}, fetch = EAGER)
    private Group group;

    @CreatedDate
    private LocalDate created;

    private boolean enabled;
}