package com.peaksoft.examrestapijwttoken.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    private Long id;

    private String roleName;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

//    @OneToMany(targetEntity = Teacher.class, mappedBy = "role", cascade = CascadeType.ALL)
//    private List<Teacher> teachers;

    public void addUsers(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
