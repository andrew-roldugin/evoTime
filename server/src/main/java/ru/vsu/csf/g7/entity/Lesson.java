package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "lesson_id", nullable = false, unique = true))
public class Lesson extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;

    @ManyToMany
    @JoinTable(name = "assigned_lessons",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "group_id"))
    @JsonManagedReference
    private List<StudentGroup> assignedGroups;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "time_id", referencedColumnName = "id")
    private Timetable time;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lesson_type_id", referencedColumnName = "flex_value_id")
    private FlexValue lessonType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonBackReference
    private Schedule schedule;

    @Column
    private boolean shared = false;

    @Enumerated(EnumType.STRING)
    private EWeekType weekType;
}
