package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "profile_id", nullable = false))

//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "attachment_type", discriminatorType = DiscriminatorType.STRING)

public class UserProfile extends BaseEntity<Integer> {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false, mappedBy = "profile")
    private User user;
}
