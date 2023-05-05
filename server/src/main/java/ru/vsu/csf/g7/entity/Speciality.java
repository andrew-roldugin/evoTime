package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "specialities", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "name", "specialization"}))
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "speciality_id", nullable = false, unique = true))
public class Speciality extends BaseEntity<Integer> {
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String specialization;
}
