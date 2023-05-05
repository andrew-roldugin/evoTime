package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "work_plans")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "plan_id", nullable = false, unique = true))
public class Plan extends BaseEntity<Integer> {
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final Collection<Subject> subjects = new HashSet<>();
}
