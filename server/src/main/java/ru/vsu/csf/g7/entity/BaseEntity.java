package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.AbstractAuditable_;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.OverridesAttribute;
import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
//@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity<ID extends Serializable> extends AbstractPersistable<ID>
//        extends AbstractAuditable<User, ID>
{
    @Version
    @Column
    private Long version;


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
