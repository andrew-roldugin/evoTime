package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "subject_details")
@Getter
@Setter
class SubjectDetails extends AbstractPersistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_form_id", referencedColumnName = "flex_value_id")
    private FlexValue testForm;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "flex_values_set_id")
    private FlexValuesSet totalHours;

}
