package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

import static vn.elca.training.util.constant.Constant.DATE_FORMAT;

/**
 * @author dtx
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto extends GenericDto {

    private String visa;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate birthDate;

    public EmployeeDto(Long id, Integer version, String visa, String firstName, String lastName, LocalDate birthDate) {
        super(id, version);
        this.visa = visa;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(visa, that.visa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visa);
    }
}
