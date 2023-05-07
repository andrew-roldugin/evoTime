package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

//@Entity
@Table(name = "roles")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "role_id", nullable = false))
public class Role extends BaseEntity<Integer> {
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id", referencedColumnName = "permission_id"))
    private Collection<Permission> permissions;
}
