package vn.elca.training.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dtx
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupDto extends GenericDto {

    private EmployeeDto groupLeader;

    public GroupDto(Long id, Integer version, EmployeeDto groupLeader) {
        super(id, version);
        this.groupLeader = groupLeader;
    }
}
