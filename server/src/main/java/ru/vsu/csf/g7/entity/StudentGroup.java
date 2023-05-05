package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "student_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "group_id", nullable = false, unique = true))
public class StudentGroup extends BaseEntity<Integer> {

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Student> students;

    @Column
    private String name;
}
