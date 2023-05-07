package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "subject_id", nullable = false, unique = true))
public class Subject extends BaseEntity<Long> {
    @Column
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String printableName;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private SubjectDetails info;
}

