package ru.vsu.csf.g7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity<E, ID extends Serializable> extends AbstractAuditable<E, ID> {

    protected ID id;

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
