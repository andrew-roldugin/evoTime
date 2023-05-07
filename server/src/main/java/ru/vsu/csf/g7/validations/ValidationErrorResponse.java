package ru.vsu.csf.g7.validations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private final List<Violation> violations = new ArrayList<>();

    public void addViolation(Violation violation) {
        this.violations.add(violation);
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public record Violation(String fieldName, String message) {
    }
}
