package vn.elca.training.model.validator;

import vn.elca.training.model.dto.ProjectDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author dtx
 *
 */
public class ProjectEndDateConstraintValidator implements ConstraintValidator<ProjectEndDateValid, ProjectDto> {

    @Override
    public void initialize(ProjectEndDateValid projectDeadlineValid) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(ProjectDto project, ConstraintValidatorContext constraintValidatorContext) {
        if (project.getEndDate() == null) {
            return true;
        }
        return project.getEndDate().isAfter(project.getStartDate());
    }
}
