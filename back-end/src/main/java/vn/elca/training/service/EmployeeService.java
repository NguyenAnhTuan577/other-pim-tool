package vn.elca.training.service;

import vn.elca.training.model.dto.EmployeeDto;

import java.util.List;

/**
 * @author dtx
 *
 */
public interface EmployeeService {
    EmployeeDto findOne(Long id);
    List<EmployeeDto> findAll();
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto update(EmployeeDto employeeDto);
    void delete(EmployeeDto employeeDto);
}
