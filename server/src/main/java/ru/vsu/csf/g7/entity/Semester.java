package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.WhereJoinTable;

import java.time.LocalDate;

@Entity
@Table(name = "semesters")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "semester_id", nullable = false, unique = true))
public class Semester extends BaseEntity<Integer> {
    @Column
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "flex_value_id")
    private FlexValue semesterStatus;
}
