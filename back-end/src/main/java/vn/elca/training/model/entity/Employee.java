package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author dtx
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends GenericEntity {

    @Column(nullable = false, length = 3, unique = true)
    private String visa;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(visa, employee.visa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visa);
    }
}
