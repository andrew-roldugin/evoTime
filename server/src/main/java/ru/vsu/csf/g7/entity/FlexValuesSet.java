package ru.vsu.csf.g7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flex_values_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "flex_values_set_id", nullable = false, unique = true))
public class FlexValuesSet extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private boolean enabled = true;

    @OneToMany(mappedBy = "flexValuesSet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<FlexValue> values = new ArrayList<>();

    public void addValue(FlexValue value) {
        this.values.add(value);
        value.setFlexValuesSet(this);
    }

    public void removeValue(FlexValue value) {
        this.values.remove(value);
        value.setFlexValuesSet(null);
    }
}
