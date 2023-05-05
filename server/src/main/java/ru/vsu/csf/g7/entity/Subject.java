package ru.vsu.csf.g7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "subject_id", nullable = false, unique = true))
public class Subject extends BaseEntity<Long> {
    @Column
    private String code;
    @Column(nullable = false)
    private String name;
    @Column
    private String printableName;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn
    private SubjectDetails info;
}

@Entity
@Table
class SubjectDetails {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_form_id", referencedColumnName = "flex_value_id")
    private FlexValue testForm;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToOne
    @JoinColumn(name = "flex_values_set_id")
    private FlexValuesSet totalHours;

}
