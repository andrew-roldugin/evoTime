package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lecturers")
@Getter
@Setter
//@AttributeOverride(name = "id", column = @Column(name = "lecturer_id", nullable = false, unique = true))
public class Lecturer extends User {

    @ManyToOne
    @JoinColumn(name = "lecturer_post", referencedColumnName = "flex_value_id")
    private FlexValue post;
}
