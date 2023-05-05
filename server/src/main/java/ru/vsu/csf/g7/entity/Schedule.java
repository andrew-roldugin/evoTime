package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "schedule_id", nullable = false, unique = true))
public class Schedule extends BaseEntity<Integer> {
    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Lesson> lessons;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "flex_value_id")
    private FlexValue status;
}
