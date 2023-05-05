package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;

@Log4j2
@Entity
@Table(name = "flex_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "flex_value_id", nullable = false, unique = true))
public class FlexValue extends BaseEntity<Long> {
    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, length = 1000)
    private String flexValue;

    @Column
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "flex_values_set_id")
    @JsonBackReference
    private FlexValuesSet flexValuesSet;

    @PrePersist
    private void syncAdd() {
        log.debug("@PrePersist.#FlexValue|(bidirectional sync)");
        flexValuesSet.addValue(this);
    }

    @PreRemove
    private void syncDelete() {
        log.debug("@PreRemove.#FlexValue|(bidirectional sync)");
        flexValuesSet.removeValue(this);
    }
}
