package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.elca.training.model.validator.ProjectEndDateValid;

import java.time.LocalDate;
import java.util.List;

import static vn.elca.training.util.constant.Constant.DATE_FORMAT;

/**
 * @author dtx
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ProjectEndDateValid
public class ProjectDto extends GenericDto {

    private Short projectNumber;
    private String name;
    private String customer;
    private String status;

    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate endDate;

    private GroupDto groupDto;
    private List<EmployeeDto> employeeDtos;

    public ProjectDto(Long id, Integer version, Short projectNumber, String name, String customer, String status, LocalDate startDate, LocalDate endDate, GroupDto groupDto, List<EmployeeDto> employeeDtos) {
        super(id, version);
        this.projectNumber = projectNumber;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupDto = groupDto;
        this.employeeDtos = employeeDtos;
    }
}
