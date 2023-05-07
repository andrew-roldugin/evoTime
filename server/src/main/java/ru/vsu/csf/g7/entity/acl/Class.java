package ru.vsu.csf.g7.entity.acl;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "acl_classes")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "class_id", unique = true, nullable = false))
public class Class extends AbstractPersistable<Long> {
    @Column(unique = true, nullable = false)
    private java.lang.Class class_name;
}
