package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "student_status_id", referencedColumnName = "flex_value_id")
    private FlexValue studentStatus;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "student_group_id", referencedColumnName = "group_id")
    @JsonBackReference
    private StudentGroup group;

    @Column
    private int course;

    @Column
    private int year;
}
