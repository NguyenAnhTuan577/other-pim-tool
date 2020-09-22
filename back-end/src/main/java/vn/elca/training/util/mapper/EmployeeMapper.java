package vn.elca.training.util.mapper;

import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.entity.Employee;

/**
 * @author dtx
 *
 */
@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        if (employeeDto.getId() != null) {
            employee.setId(employeeDto.getId());
        }
        if (employeeDto.getVisa() != null) {
            employee.setVisa(employeeDto.getVisa());
        }
        if (employeeDto.getFirstName() != null) {
            employee.setFirstName(employeeDto.getFirstName());
        }
        if (employeeDto.getLastName() != null) {
            employee.setLastName(employeeDto.getLastName());
        }
        if (employeeDto.getBirthDate() != null) {
            employee.setBirthDate(employeeDto.getBirthDate());
        }
        if (employeeDto.getVersion() != null) {
            employee.setVersion(employeeDto.getVersion());
        }
        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getVersion(),
                employee.getVisa(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDate()
        );
    }
}
