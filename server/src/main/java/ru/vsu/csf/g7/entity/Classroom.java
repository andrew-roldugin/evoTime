package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "room_id", unique = true, nullable = false))
public class Classroom extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String name;

    @Column
    private String printableName;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private final Collection<Lesson> lessons = new HashSet<>();
}
