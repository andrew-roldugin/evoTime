package ru.vsu.csf.g7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "time_table")
@Getter
@Setter
public class Timetable extends BaseEntity<Short> {
    @Column(name = "start_lesson", nullable = false)
    private LocalTime start;

    @Column(name = "end_lesson", nullable = false)
    private LocalTime end;

    @Column(name = "lesson_number")
    private Short number;
}
