package ru.vsu.csf.g7.entity.acl;

import jakarta.persistence.Column;
import org.springframework.data.jpa.domain.AbstractPersistable;

public class Class extends AbstractPersistable<Long> {
    @Column(unique = true, nullable = false)
    private java.lang.Class class_name;
}
