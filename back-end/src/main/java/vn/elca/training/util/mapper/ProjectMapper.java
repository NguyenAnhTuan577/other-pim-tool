package vn.elca.training.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dtx
 *
 */
@Component
public class ProjectMapper {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public Project toEntity(ProjectDto projectDto, Group group, Set<Employee> employees) {
        Project project = new Project();
        if (projectDto.getId() != null) {
            project.setId(projectDto.getId());
        }
        if (projectDto.getProjectNumber() != null) {
            project.setProjectNumber(projectDto.getProjectNumber());
        }
        if (projectDto.getName() != null) {
            project.setName(projectDto.getName());
        }
        if (projectDto.getCustomer() != null) {
            project.setCustomer(projectDto.getCustomer());
        }
        if (projectDto.getStatus() != null) {
            project.setStatus(projectDto.getStatus());
        }
        if (projectDto.getStartDate() != null) {
            project.setStartDate(projectDto.getStartDate());
        }
        if (projectDto.getEndDate() != null) {
            project.setEndDate(projectDto.getEndDate());
        }
        if (projectDto.getVersion() != null) {
            project.setVersion(projectDto.getVersion());
        }
        project.setGroup(group);
        project.setEmployees(employees);
        return project;
    }

    public ProjectDto toDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getVersion(),
                project.getProjectNumber(),
                project.getName(),
                project.getCustomer(),
                project.getStatus().toString(),
                project.getStartDate(),
                project.getEndDate(),
                groupMapper.toDto(project.getGroup()),
                project.getEmployees()
                        .stream()
                        .map(employeeMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
}
