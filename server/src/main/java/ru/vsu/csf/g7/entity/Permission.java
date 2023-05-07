package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.Collection;

//@Entity
@Table(name = "permissions")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "permission_id", nullable = false))
public class Permission extends BaseEntity<Integer> {

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
