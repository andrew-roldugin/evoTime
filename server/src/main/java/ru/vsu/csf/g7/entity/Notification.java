package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "notification_id", nullable = false, unique = true))
public class Notification extends BaseEntity<Long> {
    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Collection<NotificationAssignment> notificationAssignments;

    @Column
    private String title;

    @Column
    private String message;
}


