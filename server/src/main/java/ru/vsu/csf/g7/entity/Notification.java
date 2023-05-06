package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

//@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "notification_id", nullable = false, unique = true))
public class Notification extends BaseEntity<Long> {
    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private final Collection<NotificationAssignment> notificationAssignments = new HashSet<>();

    @Column
    private String title;

    @Column
    private String message;
}


