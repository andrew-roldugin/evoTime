package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends BaseEntity<Integer> implements UserDetails {

    @Column(name = "email", updatable = false, nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 500)
    @JsonIgnore
    private String password;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private final Collection<Role> roles = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "allowed_functions",
            joinColumns = @JoinColumn(referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "function_id"))
    @JsonManagedReference
    private final Collection<Function> functions = new HashSet<>();

    @Column(name = "is_locked")
    private boolean isAccountLocked = false;

    @Column(name = "is_deleted")
    private boolean isAccountDeleted = false;

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isAccountDeleted;
    }

    public void addRole(Role r){
        this.roles.add(r);
    }
}
