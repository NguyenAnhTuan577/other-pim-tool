package vn.elca.training.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.EmployeeRepository;

/**
 * @author dtx
 *
 */

@Component
public class GroupMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Group toEntity(GroupDto groupDto, Employee groupLeader) {
        Group group = new Group();
        if (groupDto.getId() != null) {
            group.setId(groupDto.getId());
        }
        if (groupDto.getVersion() != null) {
            group.setVersion(groupDto.getVersion());
        }
        group.setGroupLeader(groupLeader);
        return group;
    }

    public GroupDto toDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getVersion(),
                employeeMapper.toDto(group.getGroupLeader())
        );
    }
}
