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
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "group_sequence")
    private Long id;

    private String name;

    @Column(name = "date_of_start")
    private String dateOfStart;

    @Column(name = "date_of_finish")
    private String dateOfFinish;

    @ManyToMany(cascade = {PERSIST, MERGE, DETACH, REFRESH},fetch = LAZY, mappedBy = "groups")
    private List<Course>courses;

    @OneToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE}, orphanRemoval = true, fetch = EAGER, mappedBy = "group")
    private List<Student> students;

    @CreatedDate
    private LocalDate created;

    private boolean enabled;

    public void setCourses(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setGroups1(this);
    }

    public void removeCourse() {
//        this.courses.removeIf(course -> course.getId() == courseId);
        for (Course course : this.courses) {
            this.courses.remove(course);
        }
    }
}
