package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notification_assignments")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "assignment_id", nullable = false, unique = true))
class NotificationAssignment extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "assignee_id", referencedColumnName = "user_id")
    private User assignee;

    @Column
    private boolean read = false;
}
