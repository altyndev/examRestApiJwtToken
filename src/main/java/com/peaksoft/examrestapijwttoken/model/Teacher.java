package com.peaksoft.examrestapijwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "teacher_sequence")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_ame")
    private String lastName;

    @Column(name = "email")
    private String email;

    @CreatedDate
    private LocalDate created;

    private boolean enabled;

    @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
    private Course course;

//    @ManyToOne(cascade = {PERSIST, MERGE})
//    private Role role;
}