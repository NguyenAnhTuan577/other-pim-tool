package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.service.EmployeeService;

import java.util.List;

import static vn.elca.training.util.constant.Constant.EMPLOYEE_URL;
import static vn.elca.training.util.constant.Constant.ID;

/**
 * @author dtx
 *
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = EMPLOYEE_URL + ID)
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return employeeService.findOne(id);
    }

    @GetMapping(value = EMPLOYEE_URL)
    public List<EmployeeDto> getEmployees() {
        return employeeService.findAll();
    }

    @PostMapping(value = EMPLOYEE_URL)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @PutMapping(value = EMPLOYEE_URL)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.update(employeeDto);
    }

    @DeleteMapping(value = EMPLOYEE_URL)
    public void deleteEmployees(@RequestBody EmployeeDto employeeDto) {
        employeeService.delete(employeeDto);
    }
}
