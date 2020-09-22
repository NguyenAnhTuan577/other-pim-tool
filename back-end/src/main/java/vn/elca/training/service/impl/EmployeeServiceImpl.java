package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.service.EmployeeService;
import vn.elca.training.util.mapper.EmployeeMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dtx
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto findOne(Long id) {
        return employeeMapper.toDto(employeeRepository.findOne(id));
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee = employeeRepository.saveAndFlush(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee = employeeRepository.saveAndFlush(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void delete(EmployeeDto employeeDto) {
        employeeRepository.deleteByIdIn(employeeDto.getDeletedIds());
    }
}
