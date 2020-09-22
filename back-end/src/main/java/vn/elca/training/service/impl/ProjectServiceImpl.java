package vn.elca.training.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.model.exception.EmployeeDoesNotExistsException;
import vn.elca.training.model.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.mapper.ProjectMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dtx
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectDto findOne(Long id) {
        return projectMapper.toDto(projectRepository.findOne(id));
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository
                .findAllByOrderByProjectNumberAsc()
                .stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> findByStatus(String status) {
        return projectRepository
                .findProjectsByStatusOrderByProjectNumberAsc(status)
                .stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> findByNumberOrNameOrCustomerNameAndStatus(
            Short projectNumber,
            String name,
            String customer,
            String status) {
        List<Project> projects = null;
        if (status == null) {
            projects = projectRepository.findProjectsByProjectNumberOrNameContainingIgnoreCaseOrCustomerContainingIgnoreCaseOrderByProjectNumberAsc(projectNumber, name, customer);
        } else {
            projects = projectRepository.findProjects(projectNumber, name, customer, status);
        }

        return projects
                .stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> findProjects(String input, String status) {
        if (input == null) {
            if (status == null) {
                return findAll();
            } else {
                return findByStatus(status);
            }
        }
        Short projectNumber;
        try {
            projectNumber = Short.parseShort(input);
        } catch (NumberFormatException e) {
            projectNumber = 0;
        }
        String name = input;
        String customer = input;
        return findByNumberOrNameOrCustomerNameAndStatus(projectNumber, name, customer, status);
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        checkProjectNumber(projectDto.getProjectNumber());
        Set<Employee> employees = checkEmployee(projectDto);
        Group group = groupRepository.findOne(projectDto.getGroupDto().getId());
        Project project = projectMapper.toEntity(projectDto, group, employees);
        project = projectRepository.saveAndFlush(project);
        return projectMapper.toDto(project);
    }

    @Override
    @SneakyThrows
    public ProjectDto update(ProjectDto projectDto) {
        Set<Employee> employees = checkEmployee(projectDto);
        Group group = groupRepository.findOne(projectDto.getGroupDto().getId());
        Project project = projectMapper.toEntity(projectDto, group, employees);
        try {
            project = projectRepository.saveAndFlush(project);
            return projectMapper.toDto(project);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ConcurrentUpdateException(project.getProjectNumber());
        }
    }

    @Override
    public void delete(ProjectDto projectDto) {
        projectRepository.deleteByIdIn(projectDto.getDeletedIds());
    }

    @Override
    @SneakyThrows
    public void checkProjectNumber(Short projectNumber) {
        if (projectRepository.findByProjectNumber(projectNumber) != null) {
            throw new ProjectNumberAlreadyExistsException(projectNumber);
        }
    }

    @Override
    @SneakyThrows
    public Set<Employee> checkEmployee(ProjectDto projectDto) {
        Set<String> visas = projectDto
                .getEmployeeDtos()
                .stream()
                .map(EmployeeDto::getVisa)
                .collect(Collectors.toSet());

        Set<Employee> employees = employeeRepository.findByVisaIn(visas);

        Set<String> visaEmployees = employees
                .stream()
                .map(Employee::getVisa)
                .collect(Collectors.toSet());

        List<String> invalidEmployees = visas
                .stream()
                .filter(visa -> !visaEmployees.contains(visa))
                .collect(Collectors.toList());

        if (invalidEmployees.size() > 0) {
            throw new EmployeeDoesNotExistsException(invalidEmployees);
        }
        return employees;
    }
}
