package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "functions")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "function_id", nullable = false, unique = true))
public class Function extends BaseEntity<Integer> {
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "functions")
    @JsonBackReference
    private List<User> users;
}
