package com.peaksoft.examrestapijwttoken.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "course_sequence")
    private Long id;

    private String name;

    private String duration;

    @ManyToOne
    private Company company;

    @ManyToMany(cascade = {PERSIST, MERGE, DETACH, REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "groups_course", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    @OneToOne(cascade = ALL, fetch = EAGER, mappedBy = "course")
    private Teacher teacher;

    @CreatedDate
    private LocalDate created;

    private boolean enabled;

    public void setGroups1(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }
}