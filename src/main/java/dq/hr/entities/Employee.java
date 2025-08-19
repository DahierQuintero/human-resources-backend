package dq.hr.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Employee model from the system")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique employee ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Full name of employee")
    private String name;
    @Schema(description = "Department where the employee works")
    private String department;
    @Schema(description = "Employee salary")
    private Double salary;
}
