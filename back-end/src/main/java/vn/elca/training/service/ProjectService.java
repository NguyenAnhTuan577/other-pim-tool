package vn.elca.training.service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.model.exception.ProjectNumberAlreadyExistsException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author dtx
 *
 */
public interface ProjectService {
    ProjectDto findOne(Long id);
    List<ProjectDto> findAll();
    List<ProjectDto> findByStatus(String status);
    List<ProjectDto> findByNumberOrNameOrCustomerNameAndStatus(
            Short projectNumber,
            String name,
            String customer,
            String status
    );
    List<ProjectDto> findProjects(String input, String status);

    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto);
    void delete(ProjectDto projectDto);

    void checkProjectNumber(Short projectNumber);
    Set<Employee> checkEmployee(ProjectDto projectDto);
}