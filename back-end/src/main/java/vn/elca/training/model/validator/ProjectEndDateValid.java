package vn.elca.training.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author dtx
 *
 */
@Documented
@Constraint(validatedBy = ProjectEndDateConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProjectEndDateValid {

    String message() default "End date must be greater than start date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
